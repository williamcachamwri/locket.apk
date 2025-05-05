package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$$ExternalSyntheticLambda3 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ DefaultVideoFrameProcessor f$0;

    public /* synthetic */ DefaultVideoFrameProcessor$$ExternalSyntheticLambda3(DefaultVideoFrameProcessor defaultVideoFrameProcessor) {
        this.f$0 = defaultVideoFrameProcessor;
    }

    public final void run() {
        this.f$0.releaseGlObjects();
    }
}
