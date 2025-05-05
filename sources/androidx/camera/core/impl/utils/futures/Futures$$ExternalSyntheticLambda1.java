package androidx.camera.core.impl.utils.futures;

import java.util.concurrent.ScheduledFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ScheduledFuture f$0;

    public /* synthetic */ Futures$$ExternalSyntheticLambda1(ScheduledFuture scheduledFuture) {
        this.f$0 = scheduledFuture;
    }

    public final void run() {
        this.f$0.cancel(true);
    }
}
