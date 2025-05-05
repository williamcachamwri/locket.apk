package androidx.camera.view;

import androidx.concurrent.futures.CallbackToFutureAdapter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PendingValue$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ PendingValue f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ PendingValue$$ExternalSyntheticLambda0(PendingValue pendingValue, Object obj) {
        this.f$0 = pendingValue;
        this.f$1 = obj;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return this.f$0.m311lambda$setValue$0$androidxcameraviewPendingValue(this.f$1, completer);
    }
}
