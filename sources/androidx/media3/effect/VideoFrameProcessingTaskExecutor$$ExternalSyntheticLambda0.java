package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;
import java.util.concurrent.CountDownLatch;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda0 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ VideoFrameProcessingTaskExecutor f$0;
    public final /* synthetic */ CountDownLatch f$1;

    public /* synthetic */ VideoFrameProcessingTaskExecutor$$ExternalSyntheticLambda0(VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, CountDownLatch countDownLatch) {
        this.f$0 = videoFrameProcessingTaskExecutor;
        this.f$1 = countDownLatch;
    }

    public final void run() {
        this.f$0.m461lambda$flush$2$androidxmedia3effectVideoFrameProcessingTaskExecutor(this.f$1);
    }
}
