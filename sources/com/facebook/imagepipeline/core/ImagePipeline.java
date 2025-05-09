package com.facebook.imagepipeline.core;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

public class ImagePipeline {
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;
    @Nullable
    private final CallerContextVerifier mCallerContextVerifier;
    private final ImagePipelineConfigInterface mConfig;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter = new AtomicLong();
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final Supplier<Boolean> mLazyDataSource;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final RequestListener2 mRequestListener2;
    /* access modifiers changed from: private */
    public final BufferedDiskCache mSmallImageBufferedDiskCache;
    private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public void init() {
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> set, Set<RequestListener2> set2, Supplier<Boolean> supplier, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> supplier2, Supplier<Boolean> supplier3, @Nullable CallerContextVerifier callerContextVerifier, ImagePipelineConfigInterface imagePipelineConfigInterface) {
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(set);
        this.mRequestListener2 = new ForwardingRequestListener2(set2);
        this.mIsPrefetchEnabledSupplier = supplier;
        this.mBitmapMemoryCache = memoryCache;
        this.mEncodedMemoryCache = memoryCache2;
        this.mMainBufferedDiskCache = bufferedDiskCache;
        this.mSmallImageBufferedDiskCache = bufferedDiskCache2;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
        this.mSuppressBitmapPrefetchingSupplier = supplier2;
        this.mLazyDataSource = supplier3;
        this.mCallerContextVerifier = callerContextVerifier;
        this.mConfig = imagePipelineConfigInterface;
    }

    public String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj, final ImageRequest.RequestLevel requestLevel) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() {
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, obj, requestLevel);
            }

            public String toString() {
                return Objects.toStringHelper((Object) this).add("uri", (Object) imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener) {
        final ImageRequest imageRequest2 = imageRequest;
        final Object obj2 = obj;
        final ImageRequest.RequestLevel requestLevel2 = requestLevel;
        final RequestListener requestListener2 = requestListener;
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() {
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest2, obj2, requestLevel2, requestListener2);
            }

            public String toString() {
                return Objects.toStringHelper((Object) this).add("uri", (Object) imageRequest2.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str) {
        final ImageRequest imageRequest2 = imageRequest;
        final Object obj2 = obj;
        final ImageRequest.RequestLevel requestLevel2 = requestLevel;
        final RequestListener requestListener2 = requestListener;
        final String str2 = str;
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() {
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest2, obj2, requestLevel2, requestListener2, str2);
            }

            public String toString() {
                return Objects.toStringHelper((Object) this).add("uri", (Object) imageRequest2.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest imageRequest, @Nullable final Object obj) {
        return new Supplier<DataSource<CloseableReference<PooledByteBuffer>>>() {
            public DataSource<CloseableReference<PooledByteBuffer>> get() {
                return ImagePipeline.this.fetchEncodedImage(imageRequest, obj);
            }

            public String toString() {
                return Objects.toStringHelper((Object) this).add("uri", (Object) imageRequest.getSourceUri()).toString();
            }
        };
    }

    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, @Nullable Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, ImageRequest.RequestLevel.FULL_FETCH, requestListener);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, (RequestListener) null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, obj, requestLevel, requestListener, (String) null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(@Nullable ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str) {
        try {
            Preconditions.checkNotNull(imageRequest);
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener, str);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, @Nullable Object obj, ImageRequest.RequestLevel requestLevel, @Nullable RequestListener requestListener, @Nullable String str, @Nullable Map<String, ?> map) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, requestLevel, obj, requestListener, str, map);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, @Nullable Object obj) {
        return fetchEncodedImage(imageRequest, obj, (RequestListener) null);
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        Preconditions.checkNotNull(imageRequest.getSourceUri());
        try {
            Producer<CloseableReference<PooledByteBuffer>> encodedImageProducerSequence = this.mProducerSequenceFactory.getEncodedImageProducerSequence(imageRequest);
            if (imageRequest.getResizeOptions() != null) {
                imageRequest = ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions((ResizeOptions) null).build();
            }
            return submitFetchRequest(encodedImageProducerSequence, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, requestListener, (String) null, (Map<String, ?>) null);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToBitmapCache(imageRequest, obj, (RequestListener) null);
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        boolean z;
        Producer<Void> producer;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("ImagePipeline#prefetchToBitmapCache");
            }
            if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> immediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateFailedDataSource;
            } else if (this.mConfig.getExperiments() == null || !this.mConfig.getExperiments().getPrefetchShortcutEnabled() || !isInBitmapMemoryCache(imageRequest)) {
                Boolean shouldDecodePrefetches = imageRequest.shouldDecodePrefetches();
                if (shouldDecodePrefetches != null) {
                    z = !shouldDecodePrefetches.booleanValue();
                } else {
                    z = this.mSuppressBitmapPrefetchingSupplier.get().booleanValue();
                }
                if (z) {
                    producer = this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest);
                } else {
                    producer = this.mProducerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest);
                }
                DataSource<Void> submitPrefetchRequest = submitPrefetchRequest(producer, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, Priority.MEDIUM, requestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return submitPrefetchRequest;
            } else {
                DataSource<Void> immediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateSuccessfulDataSource;
            }
        } catch (Exception e) {
            DataSource<Void> immediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return immediateFailedDataSource2;
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return prefetchToDiskCache(imageRequest, obj, Priority.MEDIUM, requestListener);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, Priority priority) {
        return prefetchToDiskCache(imageRequest, obj, priority, (RequestListener) null);
    }

    public DataSource<Void> prefetchToDiskCache(@Nullable ImageRequest imageRequest, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        if (imageRequest == null) {
            return DataSources.immediateFailedDataSource(new NullPointerException("imageRequest is null"));
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority, requestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj) {
        return prefetchToEncodedCache(imageRequest, obj, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, @Nullable RequestListener requestListener) {
        return prefetchToEncodedCache(imageRequest, obj, Priority.MEDIUM, requestListener);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, Priority priority) {
        return prefetchToEncodedCache(imageRequest, obj, priority, (RequestListener) null);
    }

    public DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("ImagePipeline#prefetchToEncodedCache");
            }
            if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> immediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateFailedDataSource;
            } else if (this.mConfig.getExperiments() == null || !this.mConfig.getExperiments().getPrefetchShortcutEnabled() || !isInEncodedMemoryCache(imageRequest)) {
                DataSource<Void> submitPrefetchRequest = submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, obj, priority, requestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return submitPrefetchRequest;
            } else {
                DataSource<Void> immediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return immediateSuccessfulDataSource;
            }
        } catch (Exception e) {
            DataSource<Void> immediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return immediateFailedDataSource2;
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    public void evictFromMemoryCache(Uri uri) {
        Predicate<CacheKey> predicateForUri = predicateForUri(uri);
        this.mBitmapMemoryCache.removeAll(predicateForUri);
        this.mEncodedMemoryCache.removeAll(predicateForUri);
    }

    public void evictFromDiskCache(Uri uri) {
        evictFromDiskCache((ImageRequest) Preconditions.checkNotNull(ImageRequest.fromUri(uri)));
    }

    public void evictFromDiskCache(@Nullable ImageRequest imageRequest) {
        if (imageRequest != null) {
            CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, (Object) null);
            this.mMainBufferedDiskCache.remove(encodedCacheKey);
            this.mSmallImageBufferedDiskCache.remove(encodedCacheKey);
        }
    }

    public void evictFromCache(Uri uri) {
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public void clearMemoryCaches() {
        AnonymousClass5 r0 = new Predicate<CacheKey>() {
            public boolean apply(CacheKey cacheKey) {
                return true;
            }
        };
        this.mBitmapMemoryCache.removeAll(r0);
        this.mEncodedMemoryCache.removeAll(r0);
    }

    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }

    public long getUsedDiskCacheSize() {
        return this.mMainBufferedDiskCache.getSize() + this.mSmallImageBufferedDiskCache.getSize();
    }

    public void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mBitmapMemoryCache.contains(predicateForUri(uri));
    }

    public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        return this.mBitmapMemoryCache;
    }

    public boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<CloseableImage> closeableReference = this.mBitmapMemoryCache.get(this.mCacheKeyFactory.getBitmapCacheKey(imageRequest, (Object) null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
        }
    }

    public boolean isInEncodedMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mEncodedMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInEncodedMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<PooledByteBuffer> closeableReference = this.mEncodedMemoryCache.get(this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, (Object) null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
        }
    }

    public boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) {
        return isInDiskCacheSync(ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build());
    }

    public boolean isInDiskCacheSync(ImageRequest imageRequest) {
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, (Object) null);
        int i = AnonymousClass9.$SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[imageRequest.getCacheChoice().ordinal()];
        if (i == 1) {
            return this.mMainBufferedDiskCache.diskCheckSync(encodedCacheKey);
        }
        if (i != 2) {
            return false;
        }
        return this.mSmallImageBufferedDiskCache.diskCheckSync(encodedCacheKey);
    }

    /* renamed from: com.facebook.imagepipeline.core.ImagePipeline$9  reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice[] r0 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice = r0
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice r1 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.imagepipeline.request.ImageRequest$CacheChoice r1 = com.facebook.imagepipeline.request.ImageRequest.CacheChoice.SMALL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.AnonymousClass9.<clinit>():void");
        }
    }

    public DataSource<Boolean> isInDiskCache(Uri uri) {
        return isInDiskCache((ImageRequest) Preconditions.checkNotNull(ImageRequest.fromUri(uri)));
    }

    public DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        final CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, (Object) null);
        final SimpleDataSource create = SimpleDataSource.create();
        this.mMainBufferedDiskCache.contains(encodedCacheKey).continueWithTask(new Continuation<Boolean, Task<Boolean>>() {
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                if (task.isCancelled() || task.isFaulted() || !task.getResult().booleanValue()) {
                    return ImagePipeline.this.mSmallImageBufferedDiskCache.contains(encodedCacheKey);
                }
                return Task.forResult(true);
            }
        }).continueWith(new Continuation<Boolean, Void>() {
            public Void then(Task<Boolean> task) throws Exception {
                create.setResult(Boolean.valueOf(!task.isCancelled() && !task.isFaulted() && task.getResult().booleanValue()));
                return null;
            }
        });
        return create;
    }

    @Nullable
    public CacheKey getCacheKey(@Nullable ImageRequest imageRequest, @Nullable Object obj) {
        CacheKey cacheKey;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#getCacheKey");
        }
        CacheKeyFactory cacheKeyFactory = this.mCacheKeyFactory;
        if (cacheKeyFactory == null || imageRequest == null) {
            cacheKey = null;
        } else {
            cacheKey = imageRequest.getPostprocessor() != null ? cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, obj) : cacheKeyFactory.getBitmapCacheKey(imageRequest, obj);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return cacheKey;
    }

    @Nullable
    public CloseableReference<CloseableImage> getCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return null;
        }
        CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
        if (closeableReference == null || closeableReference.get().getQualityInfo().isOfFullQuality()) {
            return closeableReference;
        }
        closeableReference.close();
        return null;
    }

    public boolean hasCachedImage(@Nullable CacheKey cacheKey) {
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.mBitmapMemoryCache;
        if (memoryCache == null || cacheKey == null) {
            return false;
        }
        return memoryCache.contains(cacheKey);
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, @Nullable RequestListener requestListener, @Nullable String str) {
        return submitFetchRequest(producer, imageRequest, requestLevel, obj, requestListener, str, (Map<String, ?>) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> com.facebook.datasource.DataSource<com.facebook.common.references.CloseableReference<T>> submitFetchRequest(com.facebook.imagepipeline.producers.Producer<com.facebook.common.references.CloseableReference<T>> r15, com.facebook.imagepipeline.request.ImageRequest r16, com.facebook.imagepipeline.request.ImageRequest.RequestLevel r17, @javax.annotation.Nullable java.lang.Object r18, @javax.annotation.Nullable com.facebook.imagepipeline.listener.RequestListener r19, @javax.annotation.Nullable java.lang.String r20, @javax.annotation.Nullable java.util.Map<java.lang.String, ?> r21) {
        /*
            r14 = this;
            r1 = r14
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto L_0x000c
            java.lang.String r0 = "ImagePipeline#submitFetchRequest"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        L_0x000c:
            com.facebook.imagepipeline.producers.InternalRequestListener r0 = new com.facebook.imagepipeline.producers.InternalRequestListener
            r3 = r16
            r2 = r19
            com.facebook.imagepipeline.listener.RequestListener r2 = r14.getRequestListenerForRequest(r3, r2)
            com.facebook.imagepipeline.listener.RequestListener2 r4 = r1.mRequestListener2
            r0.<init>(r2, r4)
            com.facebook.callercontext.CallerContextVerifier r2 = r1.mCallerContextVerifier
            r4 = 0
            r7 = r18
            if (r2 == 0) goto L_0x0025
            r2.verifyCallerContext(r7, r4)
        L_0x0025:
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r2 = r16.getLowestPermittedRequestLevel()     // Catch:{ Exception -> 0x0073 }
            r5 = r17
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r8 = com.facebook.imagepipeline.request.ImageRequest.RequestLevel.getMax(r2, r5)     // Catch:{ Exception -> 0x0073 }
            com.facebook.imagepipeline.producers.SettableProducerContext r13 = new com.facebook.imagepipeline.producers.SettableProducerContext     // Catch:{ Exception -> 0x0073 }
            java.lang.String r5 = r14.generateUniqueFutureId()     // Catch:{ Exception -> 0x0073 }
            r9 = 0
            boolean r2 = r16.getProgressiveRenderingEnabled()     // Catch:{ Exception -> 0x0073 }
            if (r2 != 0) goto L_0x0049
            android.net.Uri r2 = r16.getSourceUri()     // Catch:{ Exception -> 0x0073 }
            boolean r2 = com.facebook.common.util.UriUtil.isNetworkUri(r2)     // Catch:{ Exception -> 0x0073 }
            if (r2 != 0) goto L_0x0047
            goto L_0x0049
        L_0x0047:
            r10 = r4
            goto L_0x004b
        L_0x0049:
            r2 = 1
            r10 = r2
        L_0x004b:
            com.facebook.imagepipeline.common.Priority r11 = r16.getPriority()     // Catch:{ Exception -> 0x0073 }
            com.facebook.imagepipeline.core.ImagePipelineConfigInterface r12 = r1.mConfig     // Catch:{ Exception -> 0x0073 }
            r2 = r13
            r3 = r16
            r4 = r5
            r5 = r20
            r6 = r0
            r7 = r18
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0073 }
            r2 = r21
            r13.putExtras(r2)     // Catch:{ Exception -> 0x0073 }
            r2 = r15
            com.facebook.datasource.DataSource r0 = com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter.create(r15, r13, r0)     // Catch:{ Exception -> 0x0073 }
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L_0x0070
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x0070:
            return r0
        L_0x0071:
            r0 = move-exception
            goto L_0x0082
        L_0x0073:
            r0 = move-exception
            com.facebook.datasource.DataSource r0 = com.facebook.datasource.DataSources.immediateFailedDataSource(r0)     // Catch:{ all -> 0x0071 }
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L_0x0081
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x0081:
            return r0
        L_0x0082:
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L_0x008b
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x008b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.submitFetchRequest(com.facebook.imagepipeline.producers.Producer, com.facebook.imagepipeline.request.ImageRequest, com.facebook.imagepipeline.request.ImageRequest$RequestLevel, java.lang.Object, com.facebook.imagepipeline.listener.RequestListener, java.lang.String, java.util.Map):com.facebook.datasource.DataSource");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> com.facebook.datasource.DataSource<com.facebook.common.references.CloseableReference<T>> submitFetchRequest(com.facebook.imagepipeline.producers.Producer<com.facebook.common.references.CloseableReference<T>> r15, com.facebook.imagepipeline.request.ImageRequest r16, com.facebook.imagepipeline.request.ImageRequest.RequestLevel r17, @javax.annotation.Nullable java.lang.Object r18, @javax.annotation.Nullable com.facebook.imagepipeline.listener.RequestListener r19, @javax.annotation.Nullable java.util.Map<java.lang.String, ?> r20) {
        /*
            r14 = this;
            r1 = r14
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto L_0x000c
            java.lang.String r0 = "ImagePipeline#submitFetchRequest"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        L_0x000c:
            com.facebook.imagepipeline.producers.InternalRequestListener r0 = new com.facebook.imagepipeline.producers.InternalRequestListener
            r3 = r16
            r2 = r19
            com.facebook.imagepipeline.listener.RequestListener r2 = r14.getRequestListenerForRequest(r3, r2)
            com.facebook.imagepipeline.listener.RequestListener2 r4 = r1.mRequestListener2
            r0.<init>(r2, r4)
            com.facebook.callercontext.CallerContextVerifier r2 = r1.mCallerContextVerifier
            r4 = 0
            r7 = r18
            if (r2 == 0) goto L_0x0025
            r2.verifyCallerContext(r7, r4)
        L_0x0025:
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r2 = r16.getLowestPermittedRequestLevel()     // Catch:{ Exception -> 0x006e }
            r5 = r17
            com.facebook.imagepipeline.request.ImageRequest$RequestLevel r8 = com.facebook.imagepipeline.request.ImageRequest.RequestLevel.getMax(r2, r5)     // Catch:{ Exception -> 0x006e }
            com.facebook.imagepipeline.producers.SettableProducerContext r13 = new com.facebook.imagepipeline.producers.SettableProducerContext     // Catch:{ Exception -> 0x006e }
            java.lang.String r5 = r14.generateUniqueFutureId()     // Catch:{ Exception -> 0x006e }
            r6 = 0
            r9 = 0
            boolean r2 = r16.getProgressiveRenderingEnabled()     // Catch:{ Exception -> 0x006e }
            if (r2 != 0) goto L_0x004a
            android.net.Uri r2 = r16.getSourceUri()     // Catch:{ Exception -> 0x006e }
            boolean r2 = com.facebook.common.util.UriUtil.isNetworkUri(r2)     // Catch:{ Exception -> 0x006e }
            if (r2 != 0) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            r10 = r4
            goto L_0x004c
        L_0x004a:
            r2 = 1
            r10 = r2
        L_0x004c:
            com.facebook.imagepipeline.common.Priority r11 = r16.getPriority()     // Catch:{ Exception -> 0x006e }
            com.facebook.imagepipeline.core.ImagePipelineConfigInterface r12 = r1.mConfig     // Catch:{ Exception -> 0x006e }
            r2 = r13
            r3 = r16
            r4 = r5
            r5 = r6
            r6 = r0
            r7 = r18
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x006e }
            r2 = r15
            com.facebook.datasource.DataSource r0 = com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter.create(r15, r13, r0)     // Catch:{ Exception -> 0x006e }
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L_0x006b
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x006b:
            return r0
        L_0x006c:
            r0 = move-exception
            goto L_0x007d
        L_0x006e:
            r0 = move-exception
            com.facebook.datasource.DataSource r0 = com.facebook.datasource.DataSources.immediateFailedDataSource(r0)     // Catch:{ all -> 0x006c }
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L_0x007c
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x007c:
            return r0
        L_0x007d:
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L_0x0086
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x0086:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.submitFetchRequest(com.facebook.imagepipeline.producers.Producer, com.facebook.imagepipeline.request.ImageRequest, com.facebook.imagepipeline.request.ImageRequest$RequestLevel, java.lang.Object, com.facebook.imagepipeline.listener.RequestListener, java.util.Map):com.facebook.datasource.DataSource");
    }

    public <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producer, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        try {
            DataSource<CloseableReference<T>> create = CloseableProducerToDataSourceAdapter.create(producer, settableProducerContext, new InternalRequestListener(requestListener, this.mRequestListener2));
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return create;
        } catch (Exception e) {
            DataSource<CloseableReference<T>> immediateFailedDataSource = DataSources.immediateFailedDataSource(e);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return immediateFailedDataSource;
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    public ProducerSequenceFactory getProducerSequenceFactory() {
        return this.mProducerSequenceFactory;
    }

    private DataSource<Void> submitPrefetchRequest(Producer<Void> producer, ImageRequest imageRequest, ImageRequest.RequestLevel requestLevel, @Nullable Object obj, Priority priority, @Nullable RequestListener requestListener) {
        ImageRequest imageRequest2 = imageRequest;
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        Object obj2 = obj;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(obj2, true);
        }
        try {
            ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), requestLevel);
            Producer<Void> producer2 = producer;
            return ProducerToDataSourceAdapter.create(producer, new SettableProducerContext(imageRequest, generateUniqueFutureId(), internalRequestListener, obj, max, true, this.mConfig.getExperiments() != null && this.mConfig.getExperiments().getAllowProgressiveOnPrefetch() && imageRequest.getProgressiveRenderingEnabled(), priority, this.mConfig), internalRequestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public RequestListener getRequestListenerForRequest(ImageRequest imageRequest, @Nullable RequestListener requestListener) {
        if (requestListener == null) {
            if (imageRequest.getRequestListener() == null) {
                return this.mRequestListener;
            }
            return new ForwardingRequestListener(this.mRequestListener, imageRequest.getRequestListener());
        } else if (imageRequest.getRequestListener() == null) {
            return new ForwardingRequestListener(this.mRequestListener, requestListener);
        } else {
            return new ForwardingRequestListener(this.mRequestListener, requestListener, imageRequest.getRequestListener());
        }
    }

    public RequestListener getCombinedRequestListener(@Nullable RequestListener requestListener) {
        if (requestListener == null) {
            return this.mRequestListener;
        }
        return new ForwardingRequestListener(this.mRequestListener, requestListener);
    }

    private Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate<CacheKey>() {
            public boolean apply(CacheKey cacheKey) {
                return cacheKey.containsUri(uri);
            }
        };
    }

    public void pause() {
        this.mThreadHandoffProducerQueue.startQueueing();
    }

    public void resume() {
        this.mThreadHandoffProducerQueue.stopQueuing();
    }

    public boolean isPaused() {
        return this.mThreadHandoffProducerQueue.isQueueing();
    }

    public Supplier<Boolean> isLazyDataSource() {
        return this.mLazyDataSource;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public ImagePipelineConfigInterface getConfig() {
        return this.mConfig;
    }
}
