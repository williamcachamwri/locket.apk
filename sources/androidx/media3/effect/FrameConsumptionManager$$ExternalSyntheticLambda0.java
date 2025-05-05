package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FrameConsumptionManager$$ExternalSyntheticLambda0 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ GlShaderProgram f$0;

    public /* synthetic */ FrameConsumptionManager$$ExternalSyntheticLambda0(GlShaderProgram glShaderProgram) {
        this.f$0 = glShaderProgram;
    }

    public final void run() {
        this.f$0.signalEndOfCurrentInputStream();
    }
}
