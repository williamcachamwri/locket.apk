package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ VideoFrameProcessingTaskExecutor f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ VideoFrameProcessingTaskExecutor.Task f$2;

    public /* synthetic */ VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda4(VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, boolean z, VideoFrameProcessingTaskExecutor.Task task) {
        this.f$0 = videoFrameProcessingTaskExecutor;
        this.f$1 = z;
        this.f$2 = task;
    }

    public final void run() {
        this.f$0.m463lambda$wrapTaskAndSubmitToExecutorService$3$androidxmedia3effectVideoFrameProcessingTaskExecutor(this.f$1, this.f$2);
    }
}
