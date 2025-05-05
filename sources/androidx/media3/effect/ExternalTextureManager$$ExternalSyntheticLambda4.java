package androidx.media3.effect;

import android.graphics.SurfaceTexture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExternalTextureManager$$ExternalSyntheticLambda4 implements SurfaceTexture.OnFrameAvailableListener {
    public final /* synthetic */ ExternalTextureManager f$0;
    public final /* synthetic */ VideoFrameProcessingTaskExecutor f$1;

    public /* synthetic */ ExternalTextureManager$$ExternalSyntheticLambda4(ExternalTextureManager externalTextureManager, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor) {
        this.f$0 = externalTextureManager;
        this.f$1 = videoFrameProcessingTaskExecutor;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.f$0.m426lambda$new$1$androidxmedia3effectExternalTextureManager(this.f$1, surfaceTexture);
    }
}
