package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.CancellationSignal;
import android.util.Size;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class LocalThumbnailBitmapProducer implements Producer<CloseableReference<CloseableImage>> {
    static final String CREATED_THUMBNAIL = "createdThumbnail";
    public static final String PRODUCER_NAME = "LocalThumbnailBitmapProducer";
    /* access modifiers changed from: private */
    public final ContentResolver mContentResolver;
    private final Executor mExecutor;

    public LocalThumbnailBitmapProducer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, "thumbnail_bitmap");
        final CancellationSignal cancellationSignal = new CancellationSignal();
        final ProducerContext producerContext2 = producerContext;
        final AnonymousClass1 r0 = new StatefulProducerRunnable<CloseableReference<CloseableImage>>(consumer, producerListener, producerContext, PRODUCER_NAME) {
            /* access modifiers changed from: protected */
            public void onSuccess(@Nullable CloseableReference<CloseableImage> closeableReference) {
                super.onSuccess(closeableReference);
                producerListener.onUltimateProducerReached(producerContext2, LocalThumbnailBitmapProducer.PRODUCER_NAME, closeableReference != null);
                producerContext2.putOriginExtra(ImagesContract.LOCAL);
            }

            /* access modifiers changed from: protected */
            public void onFailure(Exception exc) {
                super.onFailure(exc);
                producerListener.onUltimateProducerReached(producerContext2, LocalThumbnailBitmapProducer.PRODUCER_NAME, false);
                producerContext2.putOriginExtra(ImagesContract.LOCAL);
            }

            /* access modifiers changed from: protected */
            @Nullable
            public CloseableReference<CloseableImage> getResult() throws IOException {
                Bitmap loadThumbnail = LocalThumbnailBitmapProducer.this.mContentResolver.loadThumbnail(imageRequest.getSourceUri(), new Size(imageRequest.getPreferredWidth(), imageRequest.getPreferredHeight()), cancellationSignal);
                if (loadThumbnail == null) {
                    return null;
                }
                CloseableStaticBitmap of = CloseableStaticBitmap.of(loadThumbnail, (ResourceReleaser<Bitmap>) SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0);
                producerContext2.putExtra("image_format", "thumbnail");
                of.putExtras(producerContext2.getExtras());
                return CloseableReference.of(of);
            }

            /* access modifiers changed from: protected */
            public void onCancellation() {
                super.onCancellation();
                cancellationSignal.cancel();
            }

            /* access modifiers changed from: protected */
            public Map<String, String> getExtraMapOnSuccess(@Nullable CloseableReference<CloseableImage> closeableReference) {
                return ImmutableMap.of(LocalThumbnailBitmapProducer.CREATED_THUMBNAIL, String.valueOf(closeableReference != null));
            }

            /* access modifiers changed from: protected */
            public void disposeResult(@Nullable CloseableReference<CloseableImage> closeableReference) {
                CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                r0.cancel();
            }
        });
        this.mExecutor.execute(r0);
    }
}
