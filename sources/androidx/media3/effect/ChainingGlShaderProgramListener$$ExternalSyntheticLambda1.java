package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ChainingGlShaderProgramListener$$ExternalSyntheticLambda1 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ ChainingGlShaderProgramListener f$0;
    public final /* synthetic */ GlTextureInfo f$1;

    public /* synthetic */ ChainingGlShaderProgramListener$$ExternalSyntheticLambda1(ChainingGlShaderProgramListener chainingGlShaderProgramListener, GlTextureInfo glTextureInfo) {
        this.f$0 = chainingGlShaderProgramListener;
        this.f$1 = glTextureInfo;
    }

    public final void run() {
        this.f$0.m414lambda$onInputFrameProcessed$0$androidxmedia3effectChainingGlShaderProgramListener(this.f$1);
    }
}
