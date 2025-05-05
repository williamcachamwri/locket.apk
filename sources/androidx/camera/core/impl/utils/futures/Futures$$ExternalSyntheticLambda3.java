package androidx.camera.core.impl.utils.futures;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda3 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ ScheduledExecutorService f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ Futures$$ExternalSyntheticLambda3(ListenableFuture listenableFuture, ScheduledExecutorService scheduledExecutorService, long j) {
        this.f$0 = listenableFuture;
        this.f$1 = scheduledExecutorService;
        this.f$2 = j;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return Futures.lambda$makeTimeoutFuture$3(this.f$0, this.f$1, this.f$2, completer);
    }
}
