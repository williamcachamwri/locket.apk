package androidx.media3.effect;

import androidx.media3.common.SurfaceInfo;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FinalShaderProgramWrapper$$ExternalSyntheticLambda6 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ FinalShaderProgramWrapper f$0;
    public final /* synthetic */ SurfaceInfo f$1;

    public /* synthetic */ FinalShaderProgramWrapper$$ExternalSyntheticLambda6(FinalShaderProgramWrapper finalShaderProgramWrapper, SurfaceInfo surfaceInfo) {
        this.f$0 = finalShaderProgramWrapper;
        this.f$1 = surfaceInfo;
    }

    public final void run() {
        this.f$0.m440lambda$setOutputSurfaceInfo$2$androidxmedia3effectFinalShaderProgramWrapper(this.f$1);
    }
}
