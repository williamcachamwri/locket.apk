package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$$ExternalSyntheticLambda1 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ FinalShaderProgramWrapper f$0;

    public /* synthetic */ DefaultVideoFrameProcessor$$ExternalSyntheticLambda1(FinalShaderProgramWrapper finalShaderProgramWrapper) {
        this.f$0 = finalShaderProgramWrapper;
    }

    public final void run() {
        this.f$0.flush();
    }
}
