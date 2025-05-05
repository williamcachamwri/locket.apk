package androidx.camera.core.impl.utils;

import java.util.concurrent.CountDownLatch;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Threads$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ CountDownLatch f$1;

    public /* synthetic */ Threads$$ExternalSyntheticLambda0(Runnable runnable, CountDownLatch countDownLatch) {
        this.f$0 = runnable;
        this.f$1 = countDownLatch;
    }

    public final void run() {
        Threads.lambda$runOnMainSync$0(this.f$0, this.f$1);
    }
}
