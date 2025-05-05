package androidx.camera.video;

import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Recorder$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ Recorder$$ExternalSyntheticLambda5(Executor executor, Runnable runnable) {
        this.f$0 = executor;
        this.f$1 = runnable;
    }

    public final void run() {
        this.f$0.execute(this.f$1);
    }
}
