package androidx.camera.core.impl.utils.futures;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ ListenableFuture f$3;

    public /* synthetic */ Futures$$ExternalSyntheticLambda7(CallbackToFutureAdapter.Completer completer, Object obj, boolean z, ListenableFuture listenableFuture) {
        this.f$0 = completer;
        this.f$1 = obj;
        this.f$2 = z;
        this.f$3 = listenableFuture;
    }

    public final void run() {
        Futures.lambda$makeTimeoutFuture$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
