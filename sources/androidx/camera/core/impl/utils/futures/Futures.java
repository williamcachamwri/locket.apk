package androidx.camera.core.impl.utils.futures;

import androidx.arch.core.util.Function;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.ImmediateFuture;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class Futures {
    private static final Function<?, ?> IDENTITY_FUNCTION = new Function<Object, Object>() {
        public Object apply(Object obj) {
            return obj;
        }
    };

    public static <V> ListenableFuture<V> immediateFuture(V v) {
        if (v == null) {
            return ImmediateFuture.nullFuture();
        }
        return new ImmediateFuture.ImmediateSuccessfulFuture(v);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable th) {
        return new ImmediateFuture.ImmediateFailedFuture(th);
    }

    public static <V> ScheduledFuture<V> immediateFailedScheduledFuture(Throwable th) {
        return new ImmediateFuture.ImmediateFailedScheduledFuture(th);
    }

    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        ChainingListenableFuture chainingListenableFuture = new ChainingListenableFuture(asyncFunction, listenableFuture);
        listenableFuture.addListener(chainingListenableFuture, executor);
        return chainingListenableFuture;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, final Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        return transformAsync(listenableFuture, new AsyncFunction<I, O>() {
            public ListenableFuture<O> apply(I i) {
                return Futures.immediateFuture(Function.this.apply(i));
            }
        }, executor);
    }

    public static <V> void propagate(ListenableFuture<V> listenableFuture, CallbackToFutureAdapter.Completer<V> completer) {
        propagateTransform(listenableFuture, IDENTITY_FUNCTION, completer, CameraXExecutors.directExecutor());
    }

    public static <I, O> void propagateTransform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, CallbackToFutureAdapter.Completer<O> completer, Executor executor) {
        propagateTransform(true, listenableFuture, function, completer, executor);
    }

    /* access modifiers changed from: private */
    public static <I, O> void propagateTransform(boolean z, final ListenableFuture<I> listenableFuture, final Function<? super I, ? extends O> function, final CallbackToFutureAdapter.Completer<O> completer, Executor executor) {
        Preconditions.checkNotNull(listenableFuture);
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(completer);
        Preconditions.checkNotNull(executor);
        addCallback(listenableFuture, new FutureCallback<I>() {
            public void onSuccess(I i) {
                try {
                    CallbackToFutureAdapter.Completer.this.set(function.apply(i));
                } catch (Throwable th) {
                    CallbackToFutureAdapter.Completer.this.setException(th);
                }
            }

            public void onFailure(Throwable th) {
                CallbackToFutureAdapter.Completer.this.setException(th);
            }
        }, executor);
        if (z) {
            completer.addCancellationListener(new Runnable() {
                public void run() {
                    ListenableFuture.this.cancel(true);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> listenableFuture) {
        Preconditions.checkNotNull(listenableFuture);
        if (listenableFuture.isDone()) {
            return listenableFuture;
        }
        return CallbackToFutureAdapter.getFuture(new Futures$$ExternalSyntheticLambda6(listenableFuture));
    }

    public static <V> ListenableFuture<List<V>> successfulAsList(Collection<? extends ListenableFuture<? extends V>> collection) {
        return new ListFuture(new ArrayList(collection), false, CameraXExecutors.directExecutor());
    }

    public static <V> ListenableFuture<List<V>> allAsList(Collection<? extends ListenableFuture<? extends V>> collection) {
        return new ListFuture(new ArrayList(collection), true, CameraXExecutors.directExecutor());
    }

    public static <V> void addCallback(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new CallbackListener(listenableFuture, futureCallback), executor);
    }

    private static final class CallbackListener<V> implements Runnable {
        final FutureCallback<? super V> mCallback;
        final Future<V> mFuture;

        CallbackListener(Future<V> future, FutureCallback<? super V> futureCallback) {
            this.mFuture = future;
            this.mCallback = futureCallback;
        }

        public void run() {
            try {
                this.mCallback.onSuccess(Futures.getDone(this.mFuture));
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                if (cause == null) {
                    this.mCallback.onFailure(e);
                } else {
                    this.mCallback.onFailure(cause);
                }
            } catch (Error | RuntimeException e2) {
                this.mCallback.onFailure(e2);
            }
        }

        public String toString() {
            return getClass().getSimpleName() + "," + this.mCallback;
        }
    }

    public static <V> V getDone(Future<V> future) throws ExecutionException {
        Preconditions.checkState(future.isDone(), "Future was expected to be done, " + future);
        return getUninterruptibly(future);
    }

    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    public static <V> ListenableFuture<V> makeTimeoutFuture(long j, ScheduledExecutorService scheduledExecutorService, ListenableFuture<V> listenableFuture) {
        return CallbackToFutureAdapter.getFuture(new Futures$$ExternalSyntheticLambda3(listenableFuture, scheduledExecutorService, j));
    }

    static /* synthetic */ Object lambda$makeTimeoutFuture$3(ListenableFuture listenableFuture, ScheduledExecutorService scheduledExecutorService, long j, CallbackToFutureAdapter.Completer completer) throws Exception {
        propagate(listenableFuture, completer);
        if (!listenableFuture.isDone()) {
            listenableFuture.addListener(new Futures$$ExternalSyntheticLambda1(scheduledExecutorService.schedule(new Futures$$ExternalSyntheticLambda0(completer, listenableFuture, j), j, TimeUnit.MILLISECONDS)), CameraXExecutors.directExecutor());
        }
        return "TimeoutFuture[" + listenableFuture + "]";
    }

    public static <V> ListenableFuture<V> makeTimeoutFuture(long j, ScheduledExecutorService scheduledExecutorService, V v, boolean z, ListenableFuture<V> listenableFuture) {
        return CallbackToFutureAdapter.getFuture(new Futures$$ExternalSyntheticLambda5(listenableFuture, scheduledExecutorService, v, z, j));
    }

    static /* synthetic */ Object lambda$makeTimeoutFuture$6(ListenableFuture listenableFuture, ScheduledExecutorService scheduledExecutorService, Object obj, boolean z, long j, CallbackToFutureAdapter.Completer completer) throws Exception {
        propagate(listenableFuture, completer);
        if (!listenableFuture.isDone()) {
            listenableFuture.addListener(new Futures$$ExternalSyntheticLambda8(scheduledExecutorService.schedule(new Futures$$ExternalSyntheticLambda7(completer, obj, z, listenableFuture), j, TimeUnit.MILLISECONDS)), CameraXExecutors.directExecutor());
        }
        return "TimeoutFuture[" + listenableFuture + "]";
    }

    static /* synthetic */ void lambda$makeTimeoutFuture$4(CallbackToFutureAdapter.Completer completer, Object obj, boolean z, ListenableFuture listenableFuture) {
        completer.set(obj);
        if (z) {
            listenableFuture.cancel(true);
        }
    }

    public static <V> ListenableFuture<Void> transformAsyncOnCompletion(ListenableFuture<V> listenableFuture) {
        return CallbackToFutureAdapter.getFuture(new Futures$$ExternalSyntheticLambda4(listenableFuture));
    }

    private Futures() {
    }
}
