package androidx.camera.core.impl.utils.futures;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda5 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ ScheduledExecutorService f$1;
    public final /* synthetic */ Object f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ Futures$$ExternalSyntheticLambda5(ListenableFuture listenableFuture, ScheduledExecutorService scheduledExecutorService, Object obj, boolean z, long j) {
        this.f$0 = listenableFuture;
        this.f$1 = scheduledExecutorService;
        this.f$2 = obj;
        this.f$3 = z;
        this.f$4 = j;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return Futures.lambda$makeTimeoutFuture$6(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, completer);
    }
}
