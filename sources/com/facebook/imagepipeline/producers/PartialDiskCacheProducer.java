package com.facebook.imagepipeline.producers;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public class PartialDiskCacheProducer implements Producer<EncodedImage> {
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_CACHED_VALUE_FOUND = "cached_value_found";
    public static final String PRODUCER_NAME = "PartialDiskCacheProducer";
    private final ByteArrayPool mByteArrayPool;
    private final CacheKeyFactory mCacheKeyFactory;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public PartialDiskCacheProducer(BufferedDiskCache bufferedDiskCache, CacheKeyFactory cacheKeyFactory, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        boolean isCacheEnabled = producerContext.getImageRequest().isCacheEnabled(16);
        boolean isCacheEnabled2 = producerContext.getImageRequest().isCacheEnabled(32);
        if (isCacheEnabled || isCacheEnabled2) {
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, PRODUCER_NAME);
            CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, createUriForPartialCacheKey(imageRequest), producerContext.getCallerContext());
            if (!isCacheEnabled) {
                producerListener.onProducerFinishWithSuccess(producerContext, PRODUCER_NAME, getExtraMap(producerListener, producerContext, false, 0));
                startInputProducer(consumer, producerContext, encodedCacheKey, (EncodedImage) null);
                return;
            }
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.mDefaultBufferedDiskCache.get(encodedCacheKey, atomicBoolean).continueWith(onFinishDiskReads(consumer, producerContext, encodedCacheKey));
            subscribeTaskForRequestCancellation(atomicBoolean, producerContext);
            return;
        }
        this.mInputProducer.produceResults(consumer, producerContext);
    }

    private Continuation<EncodedImage, Void> onFinishDiskReads(Consumer<EncodedImage> consumer, ProducerContext producerContext, CacheKey cacheKey) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ProducerContext producerContext2 = producerContext;
        final Consumer<EncodedImage> consumer2 = consumer;
        final CacheKey cacheKey2 = cacheKey;
        return new Continuation<EncodedImage, Void>() {
            public Void then(Task<EncodedImage> task) throws Exception {
                if (PartialDiskCacheProducer.isTaskCancelled(task)) {
                    producerListener.onProducerFinishWithCancellation(producerContext2, PartialDiskCacheProducer.PRODUCER_NAME, (Map<String, String>) null);
                    consumer2.onCancellation();
                } else if (task.isFaulted()) {
                    producerListener.onProducerFinishWithFailure(producerContext2, PartialDiskCacheProducer.PRODUCER_NAME, task.getError(), (Map<String, String>) null);
                    PartialDiskCacheProducer.this.startInputProducer(consumer2, producerContext2, cacheKey2, (EncodedImage) null);
                } else {
                    EncodedImage result = task.getResult();
                    if (result != null) {
                        ProducerListener2 producerListener2 = producerListener;
                        ProducerContext producerContext = producerContext2;
                        producerListener2.onProducerFinishWithSuccess(producerContext, PartialDiskCacheProducer.PRODUCER_NAME, PartialDiskCacheProducer.getExtraMap(producerListener2, producerContext, true, result.getSize()));
                        BytesRange max = BytesRange.toMax(result.getSize() - 1);
                        result.setBytesRange(max);
                        int size = result.getSize();
                        ImageRequest imageRequest = producerContext2.getImageRequest();
                        if (max.contains(imageRequest.getBytesRange())) {
                            producerContext2.putOriginExtra("disk", "partial");
                            producerListener.onUltimateProducerReached(producerContext2, PartialDiskCacheProducer.PRODUCER_NAME, true);
                            consumer2.onNewResult(result, 9);
                        } else {
                            consumer2.onNewResult(result, 8);
                            PartialDiskCacheProducer.this.startInputProducer(consumer2, new SettableProducerContext(ImageRequestBuilder.fromRequest(imageRequest).setBytesRange(BytesRange.from(size - 1)).build(), producerContext2), cacheKey2, result);
                        }
                    } else {
                        ProducerListener2 producerListener22 = producerListener;
                        ProducerContext producerContext2 = producerContext2;
                        producerListener22.onProducerFinishWithSuccess(producerContext2, PartialDiskCacheProducer.PRODUCER_NAME, PartialDiskCacheProducer.getExtraMap(producerListener22, producerContext2, false, 0));
                        PartialDiskCacheProducer.this.startInputProducer(consumer2, producerContext2, cacheKey2, result);
                    }
                }
                return null;
            }
        };
    }

    /* access modifiers changed from: private */
    public void startInputProducer(Consumer<EncodedImage> consumer, ProducerContext producerContext, CacheKey cacheKey, @Nullable EncodedImage encodedImage) {
        this.mInputProducer.produceResults(new PartialDiskCacheConsumer(consumer, this.mDefaultBufferedDiskCache, cacheKey, this.mPooledByteBufferFactory, this.mByteArrayPool, encodedImage, producerContext.getImageRequest().isCacheEnabled(32)), producerContext);
    }

    /* access modifiers changed from: private */
    public static boolean isTaskCancelled(Task<?> task) {
        return task.isCancelled() || (task.isFaulted() && (task.getError() instanceof CancellationException));
    }

    @Nullable
    static Map<String, String> getExtraMap(ProducerListener2 producerListener2, ProducerContext producerContext, boolean z, int i) {
        if (!producerListener2.requiresExtraMap(producerContext, PRODUCER_NAME)) {
            return null;
        }
        if (z) {
            return ImmutableMap.of("cached_value_found", String.valueOf(z), "encodedImageSize", String.valueOf(i));
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(z));
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean atomicBoolean, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                atomicBoolean.set(true);
            }
        });
    }

    private static Uri createUriForPartialCacheKey(ImageRequest imageRequest) {
        return imageRequest.getSourceUri().buildUpon().appendQueryParameter("fresco_partial", "true").build();
    }

    private static class PartialDiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private static final int READ_SIZE = 16384;
        private final ByteArrayPool mByteArrayPool;
        private final BufferedDiskCache mDefaultBufferedDiskCache;
        private final boolean mIsDiskCacheEnabledForWrite;
        @Nullable
        private final EncodedImage mPartialEncodedImageFromCache;
        private final CacheKey mPartialImageCacheKey;
        private final PooledByteBufferFactory mPooledByteBufferFactory;

        private PartialDiskCacheConsumer(Consumer<EncodedImage> consumer, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, @Nullable EncodedImage encodedImage, boolean z) {
            super(consumer);
            this.mDefaultBufferedDiskCache = bufferedDiskCache;
            this.mPartialImageCacheKey = cacheKey;
            this.mPooledByteBufferFactory = pooledByteBufferFactory;
            this.mByteArrayPool = byteArrayPool;
            this.mPartialEncodedImageFromCache = encodedImage;
            this.mIsDiskCacheEnabledForWrite = z;
        }

        public void onNewResultImpl(@Nullable EncodedImage encodedImage, int i) {
            if (!isNotLast(i)) {
                if (this.mPartialEncodedImageFromCache != null && encodedImage != null && encodedImage.getBytesRange() != null) {
                    try {
                        sendFinalResultToConsumer(merge(this.mPartialEncodedImageFromCache, encodedImage));
                    } catch (IOException e) {
                        FLog.e(PartialDiskCacheProducer.PRODUCER_NAME, "Error while merging image data", (Throwable) e);
                        getConsumer().onFailure(e);
                    } catch (Throwable th) {
                        encodedImage.close();
                        this.mPartialEncodedImageFromCache.close();
                        throw th;
                    }
                    encodedImage.close();
                    this.mPartialEncodedImageFromCache.close();
                    this.mDefaultBufferedDiskCache.remove(this.mPartialImageCacheKey);
                } else if (!this.mIsDiskCacheEnabledForWrite || !statusHasFlag(i, 8) || !isLast(i) || encodedImage == null || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
                    getConsumer().onNewResult(encodedImage, i);
                } else {
                    this.mDefaultBufferedDiskCache.put(this.mPartialImageCacheKey, encodedImage);
                    getConsumer().onNewResult(encodedImage, i);
                }
            }
        }

        private PooledByteBufferOutputStream merge(EncodedImage encodedImage, EncodedImage encodedImage2) throws IOException {
            int i = ((BytesRange) Preconditions.checkNotNull(encodedImage2.getBytesRange())).from;
            PooledByteBufferOutputStream newOutputStream = this.mPooledByteBufferFactory.newOutputStream(encodedImage2.getSize() + i);
            copy(encodedImage.getInputStreamOrThrow(), newOutputStream, i);
            copy(encodedImage2.getInputStreamOrThrow(), newOutputStream, encodedImage2.getSize());
            return newOutputStream;
        }

        private void copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
            byte[] bArr = (byte[]) this.mByteArrayPool.get(16384);
            int i2 = i;
            while (i2 > 0) {
                try {
                    int read = inputStream.read(bArr, 0, Math.min(16384, i2));
                    if (read < 0) {
                        break;
                    } else if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        i2 -= read;
                    }
                } catch (Throwable th) {
                    this.mByteArrayPool.release(bArr);
                    throw th;
                }
            }
            this.mByteArrayPool.release(bArr);
            if (i2 > 0) {
                Locale locale = null;
                throw new IOException(String.format((Locale) null, "Failed to read %d bytes - finished %d short", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            }
        }

        private void sendFinalResultToConsumer(PooledByteBufferOutputStream pooledByteBufferOutputStream) {
            EncodedImage encodedImage;
            Throwable th;
            CloseableReference of = CloseableReference.of(pooledByteBufferOutputStream.toByteBuffer());
            try {
                encodedImage = new EncodedImage((CloseableReference<PooledByteBuffer>) of);
                try {
                    encodedImage.parseMetaData();
                    getConsumer().onNewResult(encodedImage, 1);
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely((CloseableReference<?>) of);
                } catch (Throwable th2) {
                    th = th2;
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely((CloseableReference<?>) of);
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                encodedImage = null;
                th = th4;
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely((CloseableReference<?>) of);
                throw th;
            }
        }
    }
}
