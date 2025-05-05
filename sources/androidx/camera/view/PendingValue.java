package androidx.camera.view;

import androidx.arch.core.util.Function;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Pair;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Objects;

class PendingValue<T> {
    private Pair<CallbackToFutureAdapter.Completer<Void>, T> mCompleterAndValue;

    PendingValue() {
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Void> setValue(T t) {
        Threads.checkMainThread();
        return CallbackToFutureAdapter.getFuture(new PendingValue$$ExternalSyntheticLambda0(this, t));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setValue$0$androidx-camera-view-PendingValue  reason: not valid java name */
    public /* synthetic */ Object m311lambda$setValue$0$androidxcameraviewPendingValue(Object obj, CallbackToFutureAdapter.Completer completer) throws Exception {
        Pair<CallbackToFutureAdapter.Completer<Void>, T> pair = this.mCompleterAndValue;
        if (pair != null) {
            ((CallbackToFutureAdapter.Completer) Objects.requireNonNull((CallbackToFutureAdapter.Completer) pair.first)).setCancelled();
        }
        this.mCompleterAndValue = new Pair<>(completer, obj);
        return "PendingValue " + obj;
    }

    /* access modifiers changed from: package-private */
    public void propagateIfHasValue(Function<T, ListenableFuture<Void>> function) {
        Threads.checkMainThread();
        Pair<CallbackToFutureAdapter.Completer<Void>, T> pair = this.mCompleterAndValue;
        if (pair != null) {
            Futures.propagate(function.apply(pair.second), (CallbackToFutureAdapter.Completer) Objects.requireNonNull((CallbackToFutureAdapter.Completer) this.mCompleterAndValue.first));
            this.mCompleterAndValue = null;
        }
    }
}
