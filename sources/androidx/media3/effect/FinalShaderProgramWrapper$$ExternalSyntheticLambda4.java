package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.FinalShaderProgramWrapper;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FinalShaderProgramWrapper$$ExternalSyntheticLambda4 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ FinalShaderProgramWrapper f$0;
    public final /* synthetic */ DefaultShaderProgram f$1;
    public final /* synthetic */ FinalShaderProgramWrapper.SurfaceViewWrapper f$2;
    public final /* synthetic */ GlTextureInfo f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ FinalShaderProgramWrapper$$ExternalSyntheticLambda4(FinalShaderProgramWrapper finalShaderProgramWrapper, DefaultShaderProgram defaultShaderProgram, FinalShaderProgramWrapper.SurfaceViewWrapper surfaceViewWrapper, GlTextureInfo glTextureInfo, long j) {
        this.f$0 = finalShaderProgramWrapper;
        this.f$1 = defaultShaderProgram;
        this.f$2 = surfaceViewWrapper;
        this.f$3 = glTextureInfo;
        this.f$4 = j;
    }

    public final void run() {
        this.f$0.m439lambda$renderFrameToDebugSurface$7$androidxmedia3effectFinalShaderProgramWrapper(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
