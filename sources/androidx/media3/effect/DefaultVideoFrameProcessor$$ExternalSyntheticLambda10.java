package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;
import java.util.concurrent.CountDownLatch;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$$ExternalSyntheticLambda10 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ CountDownLatch f$0;

    public /* synthetic */ DefaultVideoFrameProcessor$$ExternalSyntheticLambda10(CountDownLatch countDownLatch) {
        this.f$0 = countDownLatch;
    }

    public final void run() {
        this.f$0.countDown();
    }
}
