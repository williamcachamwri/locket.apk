package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExternalTextureManager$$ExternalSyntheticLambda2 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ ExternalTextureManager f$0;
    public final /* synthetic */ GlShaderProgram f$1;

    public /* synthetic */ ExternalTextureManager$$ExternalSyntheticLambda2(ExternalTextureManager externalTextureManager, GlShaderProgram glShaderProgram) {
        this.f$0 = externalTextureManager;
        this.f$1 = glShaderProgram;
    }

    public final void run() {
        this.f$0.m432lambda$setSamplingGlShaderProgram$2$androidxmedia3effectExternalTextureManager(this.f$1);
    }
}
