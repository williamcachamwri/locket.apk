package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TexIdTextureManager$$ExternalSyntheticLambda2 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ FrameConsumptionManager f$0;

    public /* synthetic */ TexIdTextureManager$$ExternalSyntheticLambda2(FrameConsumptionManager frameConsumptionManager) {
        this.f$0 = frameConsumptionManager;
    }

    public final void run() {
        this.f$0.onReadyToAcceptInputFrame();
    }
}
