package androidx.media3.transformer;

import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.transformer.AssetLoader;
import com.google.common.collect.ImmutableMap;
import java.util.Objects;

public final class SurfaceAssetLoader implements AssetLoader {
    public static final String MEDIA_ITEM_URI_SCHEME = "transformer_surface_asset";
    private final Callback callback;
    private Format contentFormat;
    private final EditedMediaItem editedMediaItem;
    private final Handler handler;
    private boolean isStarted;
    private boolean isVideoEndOfStreamSignaled;
    private final AssetLoader.Listener listener;
    private int progressState;
    private SampleConsumer sampleConsumer;

    public interface Callback {
        void onSurfaceAssetLoaderCreated(SurfaceAssetLoader surfaceAssetLoader);

        void onSurfaceReady(Surface surface, EditedMediaItem editedMediaItem);
    }

    public void release() {
    }

    public static final class Factory implements AssetLoader.Factory {
        private final Callback callback;

        public Factory(Callback callback2) {
            this.callback = callback2;
        }

        public AssetLoader createAssetLoader(EditedMediaItem editedMediaItem, Looper looper, AssetLoader.Listener listener, AssetLoader.CompositionSettings compositionSettings) {
            Assertions.checkState(((String) Assertions.checkNotNull(((MediaItem.LocalConfiguration) Assertions.checkNotNull(editedMediaItem.mediaItem.localConfiguration)).uri.getScheme())).equals(SurfaceAssetLoader.MEDIA_ITEM_URI_SCHEME));
            SurfaceAssetLoader surfaceAssetLoader = new SurfaceAssetLoader(editedMediaItem, looper, listener, this.callback);
            this.callback.onSurfaceAssetLoaderCreated(surfaceAssetLoader);
            return surfaceAssetLoader;
        }
    }

    private SurfaceAssetLoader(EditedMediaItem editedMediaItem2, Looper looper, AssetLoader.Listener listener2, Callback callback2) {
        this.editedMediaItem = editedMediaItem2;
        this.listener = listener2;
        this.callback = callback2;
        this.handler = new Handler(looper);
        this.progressState = 0;
    }

    public void setContentFormat(Format format) {
        Assertions.checkArgument(Objects.equals(format.sampleMimeType, MimeTypes.VIDEO_RAW));
        boolean z = true;
        Assertions.checkArgument(format.width != -1);
        if (format.height == -1) {
            z = false;
        }
        Assertions.checkArgument(z);
        Assertions.checkArgument(((ColorInfo) Assertions.checkNotNull(format.colorInfo)).isDataSpaceValid());
        this.handler.post(new SurfaceAssetLoader$$ExternalSyntheticLambda1(this, format));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setContentFormat$0$androidx-media3-transformer-SurfaceAssetLoader  reason: not valid java name */
    public /* synthetic */ void m1131lambda$setContentFormat$0$androidxmedia3transformerSurfaceAssetLoader(Format format) {
        this.contentFormat = format;
        try {
            maybeFinishPreparation();
        } catch (RuntimeException e) {
            this.listener.onError(ExportException.createForAssetLoader(e, 1000));
        }
    }

    public EditedMediaItem getEditedMediaItem() {
        return this.editedMediaItem;
    }

    public void signalEndOfInput() {
        this.handler.post(new SurfaceAssetLoader$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$signalEndOfInput$1$androidx-media3-transformer-SurfaceAssetLoader  reason: not valid java name */
    public /* synthetic */ void m1132lambda$signalEndOfInput$1$androidxmedia3transformerSurfaceAssetLoader() {
        SampleConsumer sampleConsumer2;
        try {
            if (!this.isVideoEndOfStreamSignaled && (sampleConsumer2 = this.sampleConsumer) != null) {
                this.isVideoEndOfStreamSignaled = true;
                sampleConsumer2.signalEndOfVideoInput();
            }
        } catch (RuntimeException e) {
            this.listener.onError(ExportException.createForAssetLoader(e, 1000));
        }
    }

    public void start() {
        this.isStarted = true;
        maybeFinishPreparation();
    }

    public int getProgress(ProgressHolder progressHolder) {
        return this.progressState;
    }

    public ImmutableMap<Integer, String> getDecoderNames() {
        return ImmutableMap.of();
    }

    private void maybeFinishPreparation() {
        if (this.isStarted && this.contentFormat != null) {
            this.listener.onTrackCount(1);
            this.listener.onDurationUs(C.TIME_UNSET);
            this.listener.onTrackAdded(this.contentFormat, 2);
            try {
                SampleConsumer sampleConsumer2 = (SampleConsumer) Assertions.checkNotNull(this.listener.onOutputFormat(this.contentFormat));
                this.sampleConsumer = sampleConsumer2;
                sampleConsumer2.setOnInputSurfaceReadyListener(new SurfaceAssetLoader$$ExternalSyntheticLambda2(this));
            } catch (ExportException e) {
                this.listener.onError(e);
            }
            this.progressState = 3;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$maybeFinishPreparation$2$androidx-media3-transformer-SurfaceAssetLoader  reason: not valid java name */
    public /* synthetic */ void m1130lambda$maybeFinishPreparation$2$androidxmedia3transformerSurfaceAssetLoader() {
        this.callback.onSurfaceReady(((SampleConsumer) Assertions.checkNotNull(this.sampleConsumer)).getInputSurface(), this.editedMediaItem);
    }
}
