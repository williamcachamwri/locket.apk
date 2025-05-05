package androidx.camera.core.impl;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DeferrableSurfaces$$ExternalSyntheticLambda0 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Collection f$3;

    public /* synthetic */ DeferrableSurfaces$$ExternalSyntheticLambda0(ListenableFuture listenableFuture, Executor executor, boolean z, Collection collection) {
        this.f$0 = listenableFuture;
        this.f$1 = executor;
        this.f$2 = z;
        this.f$3 = collection;
    }

    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return DeferrableSurfaces.lambda$surfaceListWithTimeout$1(this.f$0, this.f$1, this.f$2, this.f$3, completer);
    }
}
