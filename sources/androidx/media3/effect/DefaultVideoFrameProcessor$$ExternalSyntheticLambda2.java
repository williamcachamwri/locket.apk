package androidx.media3.effect;

import androidx.media3.effect.DefaultVideoFrameProcessor;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$$ExternalSyntheticLambda2 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ DefaultVideoFrameProcessor f$0;
    public final /* synthetic */ DefaultVideoFrameProcessor.InputStreamInfo f$1;

    public /* synthetic */ DefaultVideoFrameProcessor$$ExternalSyntheticLambda2(DefaultVideoFrameProcessor defaultVideoFrameProcessor, DefaultVideoFrameProcessor.InputStreamInfo inputStreamInfo) {
        this.f$0 = defaultVideoFrameProcessor;
        this.f$1 = inputStreamInfo;
    }

    public final void run() {
        this.f$0.m421lambda$registerInputStream$3$androidxmedia3effectDefaultVideoFrameProcessor(this.f$1);
    }
}
