package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TexIdTextureManager$$ExternalSyntheticLambda3 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ TexIdTextureManager f$0;
    public final /* synthetic */ GlTextureInfo f$1;

    public /* synthetic */ TexIdTextureManager$$ExternalSyntheticLambda3(TexIdTextureManager texIdTextureManager, GlTextureInfo glTextureInfo) {
        this.f$0 = texIdTextureManager;
        this.f$1 = glTextureInfo;
    }

    public final void run() {
        this.f$0.m457lambda$onInputFrameProcessed$0$androidxmedia3effectTexIdTextureManager(this.f$1);
    }
}
