package androidx.camera.core.impl.utils.futures;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Futures$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$0;
    public final /* synthetic */ ListenableFuture f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ Futures$$ExternalSyntheticLambda0(CallbackToFutureAdapter.Completer completer, ListenableFuture listenableFuture, long j) {
        this.f$0 = completer;
        this.f$1 = listenableFuture;
        this.f$2 = j;
    }

    public final Object call() {
        return Boolean.valueOf(this.f$0.setException(new TimeoutException("Future[" + this.f$1 + "] is not done within " + this.f$2 + " ms.")));
    }
}
