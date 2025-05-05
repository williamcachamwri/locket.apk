package androidx.media3.transformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.ConstantRateTimestampIterator;
import androidx.media3.common.util.Util;
import androidx.media3.transformer.AssetLoader;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class ImageAssetLoader implements AssetLoader {
    private static final int QUEUE_BITMAP_INTERVAL_MS = 10;
    private final BitmapLoader bitmapLoader;
    private final Context context;
    private final EditedMediaItem editedMediaItem;
    /* access modifiers changed from: private */
    public final AssetLoader.Listener listener;
    /* access modifiers changed from: private */
    public volatile int progress;
    private int progressState;
    /* access modifiers changed from: private */
    public final boolean retainHdrFromUltraHdrImage;
    private SampleConsumer sampleConsumer;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService scheduledExecutorService;

    public static final class Factory implements AssetLoader.Factory {
        private final BitmapLoader bitmapLoader;
        private final Context context;

        public Factory(Context context2, BitmapLoader bitmapLoader2) {
            this.context = context2;
            this.bitmapLoader = bitmapLoader2;
        }

        public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
            return new ImageAssetLoader(this.context, editedMediaItem, listener, this.bitmapLoader, compositionSettings.retainHdrFromUltraHdrImage);
        }
    }

    private ImageAssetLoader(Context context2, EditedMediaItem editedMediaItem2, AssetLoader.Listener listener2, BitmapLoader bitmapLoader2, boolean z) {
        boolean z2 = true;
        Assertions.checkState(editedMediaItem2.durationUs != C.TIME_UNSET);
        Assertions.checkState(editedMediaItem2.frameRate == -2147483647 ? false : z2);
        this.context = context2;
        this.editedMediaItem = editedMediaItem2;
        this.listener = listener2;
        this.bitmapLoader = bitmapLoader2;
        this.retainHdrFromUltraHdrImage = z;
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.progressState = 0;
    }

    public void start() {
        ListenableFuture<Bitmap> listenableFuture;
        this.progressState = 2;
        this.listener.onDurationUs(this.editedMediaItem.durationUs);
        this.listener.onTrackCount(1);
        String imageMimeType = TransformerUtil.getImageMimeType(this.context, this.editedMediaItem.mediaItem);
        if (imageMimeType == null || !this.bitmapLoader.supportsMimeType(imageMimeType)) {
            listenableFuture = Futures.immediateFailedFuture(ParserException.createForUnsupportedContainerFeature("Attempted to load a Bitmap from unsupported MIME type: " + imageMimeType));
        } else {
            listenableFuture = this.bitmapLoader.loadBitmap(((MediaItem.LocalConfiguration) Assertions.checkNotNull(this.editedMediaItem.mediaItem.localConfiguration)).uri);
        }
        Futures.addCallback(listenableFuture, new FutureCallback<Bitmap>() {
            public void onSuccess(Bitmap bitmap) {
                int unused = ImageAssetLoader.this.progress = 50;
                Format build = new Format.Builder().setHeight(bitmap.getHeight()).setWidth(bitmap.getWidth()).setSampleMimeType(MimeTypes.IMAGE_RAW).setColorInfo(ColorInfo.SRGB_BT709_FULL).build();
                Format build2 = (!ImageAssetLoader.this.retainHdrFromUltraHdrImage || Util.SDK_INT < 34 || !bitmap.hasGainmap()) ? build : build.buildUpon().setSampleMimeType(MimeTypes.IMAGE_JPEG_R).build();
                try {
                    ImageAssetLoader.this.listener.onTrackAdded(build, 2);
                    ImageAssetLoader.this.scheduledExecutorService.submit(new ImageAssetLoader$1$$ExternalSyntheticLambda0(this, bitmap, build2));
                } catch (RuntimeException e) {
                    ImageAssetLoader.this.listener.onError(ExportException.createForAssetLoader(e, 1000));
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onSuccess$0$androidx-media3-transformer-ImageAssetLoader$1  reason: not valid java name */
            public /* synthetic */ void m1127lambda$onSuccess$0$androidxmedia3transformerImageAssetLoader$1(Bitmap bitmap, Format format) {
                ImageAssetLoader.this.m1126lambda$queueBitmapInternal$1$androidxmedia3transformerImageAssetLoader(bitmap, format);
            }

            public void onFailure(Throwable th) {
                ImageAssetLoader.this.listener.onError(ExportException.createForAssetLoader(th, 2000));
            }
        }, this.scheduledExecutorService);
    }

    public int getProgress(ProgressHolder progressHolder) {
        if (this.progressState == 2) {
            progressHolder.progress = this.progress;
        }
        return this.progressState;
    }

    public ImmutableMap<Integer, String> getDecoderNames() {
        return ImmutableMap.of();
    }

    public void release() {
        this.progressState = 0;
        this.scheduledExecutorService.shutdownNow();
    }

    /* access modifiers changed from: private */
    /* renamed from: queueBitmapInternal */
    public void m1126lambda$queueBitmapInternal$1$androidxmedia3transformerImageAssetLoader(Bitmap bitmap, Format format) {
        try {
            SampleConsumer sampleConsumer2 = this.sampleConsumer;
            if (sampleConsumer2 == null) {
                this.sampleConsumer = this.listener.onOutputFormat(format);
                this.scheduledExecutorService.schedule(new ImageAssetLoader$$ExternalSyntheticLambda0(this, bitmap, format), 10, TimeUnit.MILLISECONDS);
                return;
            }
            int queueInputBitmap = sampleConsumer2.queueInputBitmap(bitmap, new ConstantRateTimestampIterator(this.editedMediaItem.durationUs, (float) this.editedMediaItem.frameRate));
            if (queueInputBitmap == 1) {
                this.progress = 100;
                this.sampleConsumer.signalEndOfVideoInput();
            } else if (queueInputBitmap == 2) {
                this.scheduledExecutorService.schedule(new ImageAssetLoader$$ExternalSyntheticLambda1(this, bitmap, format), 10, TimeUnit.MILLISECONDS);
            } else if (queueInputBitmap == 3) {
                this.progress = 100;
            } else {
                throw new IllegalStateException();
            }
        } catch (ExportException e) {
            this.listener.onError(e);
        } catch (RuntimeException e2) {
            this.listener.onError(ExportException.createForAssetLoader(e2, 1000));
        }
    }
}
