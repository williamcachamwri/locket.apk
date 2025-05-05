package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoCompositor$$ExternalSyntheticLambda0 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ DefaultVideoCompositor f$0;

    public /* synthetic */ DefaultVideoCompositor$$ExternalSyntheticLambda0(DefaultVideoCompositor defaultVideoCompositor) {
        this.f$0 = defaultVideoCompositor;
    }

    public final void run() {
        this.f$0.releaseGlObjects();
    }
}
