package androidx.camera.core.impl.utils.futures;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;

    public /* synthetic */ Futures$$ExternalSyntheticLambda2(CallbackToFutureAdapter.Completer completer) {
        this.f$0 = completer;
    }

    public final void run() {
        this.f$0.set(null);
    }
}
