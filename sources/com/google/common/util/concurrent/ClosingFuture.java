package com.google.common.util.concurrent;

import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.base.Functions;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Closeable;
import java.util.IdentityHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@DoNotMock("Use ClosingFuture.from(Futures.immediate*Future)")
public final class ClosingFuture<V> {
    private static final LazyLogger logger = new LazyLogger(ClosingFuture.class);
    /* access modifiers changed from: private */
    public final CloseableList closeables;
    /* access modifiers changed from: private */
    public final FluentFuture<V> future;
    private final AtomicReference<State> state;

    public interface AsyncClosingCallable<V> {
        ClosingFuture<V> call(DeferredCloser deferredCloser) throws Exception;
    }

    public interface AsyncClosingFunction<T, U> {
        ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness T t) throws Exception;
    }

    public interface ClosingCallable<V> {
        @ParametricNullness
        V call(DeferredCloser deferredCloser) throws Exception;
    }

    public interface ClosingFunction<T, U> {
        @ParametricNullness
        U apply(DeferredCloser deferredCloser, @ParametricNullness T t) throws Exception;
    }

    enum State {
        OPEN,
        SUBSUMED,
        WILL_CLOSE,
        CLOSING,
        CLOSED,
        WILL_CREATE_VALUE_AND_CLOSER
    }

    public interface ValueAndCloserConsumer<V> {
        void accept(ValueAndCloser<V> valueAndCloser);
    }

    public static final class DeferredCloser {
        private final CloseableList list;

        DeferredCloser(CloseableList closeableList) {
            this.list = closeableList;
        }

        @ParametricNullness
        public <C extends AutoCloseable> C eventuallyClose(@ParametricNullness C c, Executor executor) {
            Preconditions.checkNotNull(executor);
            if (c != null) {
                this.list.add((AutoCloseable) c, executor);
            }
            return c;
        }
    }

    public static final class ValueAndCloser<V> {
        private final ClosingFuture<? extends V> closingFuture;

        ValueAndCloser(ClosingFuture<? extends V> closingFuture2) {
            this.closingFuture = (ClosingFuture) Preconditions.checkNotNull(closingFuture2);
        }

        @ParametricNullness
        public V get() throws ExecutionException {
            return Futures.getDone(this.closingFuture.future);
        }

        public void closeAsync() {
            this.closingFuture.close();
        }
    }

    public static <V> ClosingFuture<V> submit(final ClosingCallable<V> closingCallable, Executor executor) {
        Preconditions.checkNotNull(closingCallable);
        final CloseableList closeableList = new CloseableList();
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(new Callable<V>() {
            @ParametricNullness
            public V call() throws Exception {
                return ClosingCallable.this.call(closeableList.closer);
            }

            public String toString() {
                return ClosingCallable.this.toString();
            }
        });
        executor.execute(create);
        return new ClosingFuture<>(create, closeableList);
    }

    public static <V> ClosingFuture<V> submitAsync(final AsyncClosingCallable<V> asyncClosingCallable, Executor executor) {
        Preconditions.checkNotNull(asyncClosingCallable);
        final CloseableList closeableList = new CloseableList();
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(new AsyncCallable<V>() {
            public ListenableFuture<V> call() throws Exception {
                CloseableList closeableList = new CloseableList();
                try {
                    ClosingFuture call = AsyncClosingCallable.this.call(closeableList.closer);
                    call.becomeSubsumedInto(closeableList);
                    return call.future;
                } finally {
                    closeableList.add(closeableList, MoreExecutors.directExecutor());
                }
            }

            public String toString() {
                return AsyncClosingCallable.this.toString();
            }
        });
        executor.execute(create);
        return new ClosingFuture<>(create, closeableList);
    }

    public static <V> ClosingFuture<V> from(ListenableFuture<V> listenableFuture) {
        return new ClosingFuture<>(listenableFuture);
    }

    @Deprecated
    public static <C extends AutoCloseable> ClosingFuture<C> eventuallyClosing(ListenableFuture<C> listenableFuture, final Executor executor) {
        Preconditions.checkNotNull(executor);
        ClosingFuture<C> closingFuture = new ClosingFuture<>(Futures.nonCancellationPropagating(listenableFuture));
        Futures.addCallback(listenableFuture, new FutureCallback<AutoCloseable>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(@CheckForNull AutoCloseable autoCloseable) {
                ClosingFuture.this.closeables.closer.eventuallyClose(autoCloseable, executor);
            }
        }, MoreExecutors.directExecutor());
        return closingFuture;
    }

    public static Combiner whenAllComplete(Iterable<? extends ClosingFuture<?>> iterable) {
        return new Combiner(false, iterable);
    }

    public static Combiner whenAllComplete(ClosingFuture<?> closingFuture, ClosingFuture<?>... closingFutureArr) {
        return whenAllComplete(Lists.asList(closingFuture, closingFutureArr));
    }

    public static Combiner whenAllSucceed(Iterable<? extends ClosingFuture<?>> iterable) {
        return new Combiner(true, iterable);
    }

    public static <V1, V2> Combiner2<V1, V2> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2) {
        return new Combiner2<>(closingFuture2);
    }

    public static <V1, V2, V3> Combiner3<V1, V2, V3> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3) {
        return new Combiner3<>(closingFuture2, closingFuture3);
    }

    public static <V1, V2, V3, V4> Combiner4<V1, V2, V3, V4> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4) {
        return new Combiner4(closingFuture2, closingFuture3, closingFuture4);
    }

    public static <V1, V2, V3, V4, V5> Combiner5<V1, V2, V3, V4, V5> whenAllSucceed(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4, ClosingFuture<V5> closingFuture5) {
        return new Combiner5(closingFuture2, closingFuture3, closingFuture4, closingFuture5);
    }

    public static Combiner whenAllSucceed(ClosingFuture<?> closingFuture, ClosingFuture<?> closingFuture2, ClosingFuture<?> closingFuture3, ClosingFuture<?> closingFuture4, ClosingFuture<?> closingFuture5, ClosingFuture<?> closingFuture6, ClosingFuture<?>... closingFutureArr) {
        return whenAllSucceed(FluentIterable.of(closingFuture, closingFuture2, closingFuture3, closingFuture4, closingFuture5, closingFuture6).append((E[]) closingFutureArr));
    }

    private ClosingFuture(ListenableFuture<V> listenableFuture) {
        this(listenableFuture, new CloseableList());
    }

    private ClosingFuture(ListenableFuture<V> listenableFuture, CloseableList closeableList) {
        this.state = new AtomicReference<>(State.OPEN);
        this.future = FluentFuture.from(listenableFuture);
        this.closeables = closeableList;
    }

    public ListenableFuture<?> statusFuture() {
        return Futures.nonCancellationPropagating(this.future.transform(Functions.constant(null), MoreExecutors.directExecutor()));
    }

    public <U> ClosingFuture<U> transform(final ClosingFunction<? super V, U> closingFunction, Executor executor) {
        Preconditions.checkNotNull(closingFunction);
        return derive(this.future.transformAsync(new AsyncFunction<V, U>(this) {
            final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = r1;
            }

            public ListenableFuture<U> apply(V v) throws Exception {
                return this.this$0.closeables.applyClosingFunction(closingFunction, v);
            }

            public String toString() {
                return closingFunction.toString();
            }
        }, executor));
    }

    public <U> ClosingFuture<U> transformAsync(final AsyncClosingFunction<? super V, U> asyncClosingFunction, Executor executor) {
        Preconditions.checkNotNull(asyncClosingFunction);
        return derive(this.future.transformAsync(new AsyncFunction<V, U>(this) {
            final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = r1;
            }

            public ListenableFuture<U> apply(V v) throws Exception {
                return this.this$0.closeables.applyAsyncClosingFunction(asyncClosingFunction, v);
            }

            public String toString() {
                return asyncClosingFunction.toString();
            }
        }, executor));
    }

    public static <V, U> AsyncClosingFunction<V, U> withoutCloser(final AsyncFunction<V, U> asyncFunction) {
        Preconditions.checkNotNull(asyncFunction);
        return new AsyncClosingFunction<V, U>() {
            public ClosingFuture<U> apply(DeferredCloser deferredCloser, V v) throws Exception {
                return ClosingFuture.from(AsyncFunction.this.apply(v));
            }
        };
    }

    public <X extends Throwable> ClosingFuture<V> catching(Class<X> cls, ClosingFunction<? super X, ? extends V> closingFunction, Executor executor) {
        return catchingMoreGeneric(cls, closingFunction, executor);
    }

    private <X extends Throwable, W extends V> ClosingFuture<V> catchingMoreGeneric(Class<X> cls, final ClosingFunction<? super X, W> closingFunction, Executor executor) {
        Preconditions.checkNotNull(closingFunction);
        return derive(this.future.catchingAsync(cls, new AsyncFunction<X, W>(this) {
            final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = r1;
            }

            public ListenableFuture<W> apply(X x) throws Exception {
                return this.this$0.closeables.applyClosingFunction(closingFunction, x);
            }

            public String toString() {
                return closingFunction.toString();
            }
        }, executor));
    }

    public <X extends Throwable> ClosingFuture<V> catchingAsync(Class<X> cls, AsyncClosingFunction<? super X, ? extends V> asyncClosingFunction, Executor executor) {
        return catchingAsyncMoreGeneric(cls, asyncClosingFunction, executor);
    }

    private <X extends Throwable, W extends V> ClosingFuture<V> catchingAsyncMoreGeneric(Class<X> cls, final AsyncClosingFunction<? super X, W> asyncClosingFunction, Executor executor) {
        Preconditions.checkNotNull(asyncClosingFunction);
        return derive(this.future.catchingAsync(cls, new AsyncFunction<X, W>(this) {
            final /* synthetic */ ClosingFuture this$0;

            {
                this.this$0 = r1;
            }

            public ListenableFuture<W> apply(X x) throws Exception {
                return this.this$0.closeables.applyAsyncClosingFunction(asyncClosingFunction, x);
            }

            public String toString() {
                return asyncClosingFunction.toString();
            }
        }, executor));
    }

    public FluentFuture<V> finishToFuture() {
        if (compareAndUpdateState(State.OPEN, State.WILL_CLOSE)) {
            logger.get().log(Level.FINER, "will close {0}", this);
            this.future.addListener(new Runnable() {
                public void run() {
                    ClosingFuture.this.checkAndUpdateState(State.WILL_CLOSE, State.CLOSING);
                    ClosingFuture.this.close();
                    ClosingFuture.this.checkAndUpdateState(State.CLOSING, State.CLOSED);
                }
            }, MoreExecutors.directExecutor());
        } else {
            int ordinal = this.state.get().ordinal();
            if (ordinal == 0) {
                throw new AssertionError();
            } else if (ordinal == 1) {
                throw new IllegalStateException("Cannot call finishToFuture() after deriving another step");
            } else if (ordinal == 2 || ordinal == 3 || ordinal == 4) {
                throw new IllegalStateException("Cannot call finishToFuture() twice");
            } else if (ordinal == 5) {
                throw new IllegalStateException("Cannot call finishToFuture() after calling finishToValueAndCloser()");
            }
        }
        return this.future;
    }

    public void finishToValueAndCloser(final ValueAndCloserConsumer<? super V> valueAndCloserConsumer, Executor executor) {
        Preconditions.checkNotNull(valueAndCloserConsumer);
        if (!compareAndUpdateState(State.OPEN, State.WILL_CREATE_VALUE_AND_CLOSER)) {
            int ordinal = this.state.get().ordinal();
            if (ordinal == 1) {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() after deriving another step");
            } else if (ordinal == 2 || ordinal == 3 || ordinal == 4) {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() after calling finishToFuture()");
            } else if (ordinal != 5) {
                throw new AssertionError(this.state);
            } else {
                throw new IllegalStateException("Cannot call finishToValueAndCloser() twice");
            }
        } else {
            this.future.addListener(new Runnable(this) {
                final /* synthetic */ ClosingFuture this$0;

                {
                    this.this$0 = r1;
                }

                public void run() {
                    ClosingFuture.provideValueAndCloser(valueAndCloserConsumer, this.this$0);
                }
            }, executor);
        }
    }

    /* access modifiers changed from: private */
    public static <C, V extends C> void provideValueAndCloser(ValueAndCloserConsumer<C> valueAndCloserConsumer, ClosingFuture<V> closingFuture) {
        valueAndCloserConsumer.accept(new ValueAndCloser(closingFuture));
    }

    public boolean cancel(boolean z) {
        logger.get().log(Level.FINER, "cancelling {0}", this);
        boolean cancel = this.future.cancel(z);
        if (cancel) {
            close();
        }
        return cancel;
    }

    /* access modifiers changed from: private */
    public void close() {
        logger.get().log(Level.FINER, "closing {0}", this);
        this.closeables.close();
    }

    private <U> ClosingFuture<U> derive(FluentFuture<U> fluentFuture) {
        ClosingFuture<U> closingFuture = new ClosingFuture<>(fluentFuture);
        becomeSubsumedInto(closingFuture.closeables);
        return closingFuture;
    }

    /* access modifiers changed from: private */
    public void becomeSubsumedInto(CloseableList closeableList) {
        checkAndUpdateState(State.OPEN, State.SUBSUMED);
        closeableList.add(this.closeables, MoreExecutors.directExecutor());
    }

    public static final class Peeker {
        private volatile boolean beingCalled;
        private final ImmutableList<ClosingFuture<?>> futures;

        private Peeker(ImmutableList<ClosingFuture<?>> immutableList) {
            this.futures = (ImmutableList) Preconditions.checkNotNull(immutableList);
        }

        @ParametricNullness
        public final <D> D getDone(ClosingFuture<D> closingFuture) throws ExecutionException {
            Preconditions.checkState(this.beingCalled);
            Preconditions.checkArgument(this.futures.contains(closingFuture));
            return Futures.getDone(closingFuture.future);
        }

        /* access modifiers changed from: private */
        @ParametricNullness
        public <V> V call(Combiner.CombiningCallable<V> combiningCallable, CloseableList closeableList) throws Exception {
            this.beingCalled = true;
            CloseableList closeableList2 = new CloseableList();
            try {
                return combiningCallable.call(closeableList2.closer, this);
            } finally {
                closeableList.add(closeableList2, MoreExecutors.directExecutor());
                this.beingCalled = false;
            }
        }

        /* access modifiers changed from: private */
        public <V> FluentFuture<V> callAsync(Combiner.AsyncCombiningCallable<V> asyncCombiningCallable, CloseableList closeableList) throws Exception {
            this.beingCalled = true;
            CloseableList closeableList2 = new CloseableList();
            try {
                ClosingFuture<V> call = asyncCombiningCallable.call(closeableList2.closer, this);
                call.becomeSubsumedInto(closeableList);
                return call.future;
            } finally {
                closeableList.add(closeableList2, MoreExecutors.directExecutor());
                this.beingCalled = false;
            }
        }
    }

    @DoNotMock("Use ClosingFuture.whenAllSucceed() or .whenAllComplete() instead.")
    public static class Combiner {
        private final boolean allMustSucceed;
        /* access modifiers changed from: private */
        public final CloseableList closeables;
        protected final ImmutableList<ClosingFuture<?>> inputs;

        public interface AsyncCombiningCallable<V> {
            ClosingFuture<V> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        public interface CombiningCallable<V> {
            @ParametricNullness
            V call(DeferredCloser deferredCloser, Peeker peeker) throws Exception;
        }

        private Combiner(boolean z, Iterable<? extends ClosingFuture<?>> iterable) {
            this.closeables = new CloseableList();
            this.allMustSucceed = z;
            this.inputs = ImmutableList.copyOf(iterable);
            for (ClosingFuture access$400 : iterable) {
                access$400.becomeSubsumedInto(this.closeables);
            }
        }

        public <V> ClosingFuture<V> call(final CombiningCallable<V> combiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>((ListenableFuture) futureCombiner().call(new Callable<V>(this) {
                final /* synthetic */ Combiner this$0;

                {
                    this.this$0 = r1;
                }

                @ParametricNullness
                public V call() throws Exception {
                    return new Peeker(this.this$0.inputs).call(combiningCallable, this.this$0.closeables);
                }

                public String toString() {
                    return combiningCallable.toString();
                }
            }, executor));
            closingFuture.closeables.add(this.closeables, MoreExecutors.directExecutor());
            return closingFuture;
        }

        public <V> ClosingFuture<V> callAsync(final AsyncCombiningCallable<V> asyncCombiningCallable, Executor executor) {
            ClosingFuture<V> closingFuture = new ClosingFuture<>((ListenableFuture) futureCombiner().callAsync(new AsyncCallable<V>(this) {
                final /* synthetic */ Combiner this$0;

                {
                    this.this$0 = r1;
                }

                public ListenableFuture<V> call() throws Exception {
                    return new Peeker(this.this$0.inputs).callAsync(asyncCombiningCallable, this.this$0.closeables);
                }

                public String toString() {
                    return asyncCombiningCallable.toString();
                }
            }, executor));
            closingFuture.closeables.add(this.closeables, MoreExecutors.directExecutor());
            return closingFuture;
        }

        private Futures.FutureCombiner<Object> futureCombiner() {
            if (this.allMustSucceed) {
                return Futures.whenAllSucceed(inputFutures());
            }
            return Futures.whenAllComplete(inputFutures());
        }

        private ImmutableList<FluentFuture<?>> inputFutures() {
            return FluentIterable.from(this.inputs).transform(new ClosingFuture$Combiner$$ExternalSyntheticLambda0()).toList();
        }
    }

    public static final class Combiner2<V1, V2> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;

        public interface AsyncClosingFunction2<V1, V2, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2) throws Exception;
        }

        public interface ClosingFunction2<V1, V2, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2) throws Exception;
        }

        private Combiner2(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2) {
            super(true, ImmutableList.of(closingFuture, closingFuture2));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction2<V1, V2, U> closingFunction2, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) {
                final /* synthetic */ Combiner2 this$0;

                {
                    this.this$0 = r1;
                }

                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction2.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2));
                }

                public String toString() {
                    return closingFunction2.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction2<V1, V2, U> asyncClosingFunction2, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) {
                final /* synthetic */ Combiner2 this$0;

                {
                    this.this$0 = r1;
                }

                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction2.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2));
                }

                public String toString() {
                    return asyncClosingFunction2.toString();
                }
            }, executor);
        }
    }

    public static final class Combiner3<V1, V2, V3> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;

        public interface AsyncClosingFunction3<V1, V2, V3, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3) throws Exception;
        }

        public interface ClosingFunction3<V1, V2, V3, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3) throws Exception;
        }

        private Combiner3(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3) {
            super(true, ImmutableList.of(closingFuture, closingFuture2, closingFuture3));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
            this.future3 = closingFuture3;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction3<V1, V2, V3, U> closingFunction3, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) {
                final /* synthetic */ Combiner3 this$0;

                {
                    this.this$0 = r1;
                }

                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction3.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3));
                }

                public String toString() {
                    return closingFunction3.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction3<V1, V2, V3, U> asyncClosingFunction3, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) {
                final /* synthetic */ Combiner3 this$0;

                {
                    this.this$0 = r1;
                }

                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction3.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3));
                }

                public String toString() {
                    return asyncClosingFunction3.toString();
                }
            }, executor);
        }
    }

    public static final class Combiner4<V1, V2, V3, V4> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;
        /* access modifiers changed from: private */
        public final ClosingFuture<V4> future4;

        public interface AsyncClosingFunction4<V1, V2, V3, V4, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4) throws Exception;
        }

        public interface ClosingFunction4<V1, V2, V3, V4, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4) throws Exception;
        }

        private Combiner4(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4) {
            super(true, ImmutableList.of(closingFuture, closingFuture2, closingFuture3, closingFuture4));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
            this.future3 = closingFuture3;
            this.future4 = closingFuture4;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction4<V1, V2, V3, V4, U> closingFunction4, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) {
                final /* synthetic */ Combiner4 this$0;

                {
                    this.this$0 = r1;
                }

                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction4.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4));
                }

                public String toString() {
                    return closingFunction4.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction4<V1, V2, V3, V4, U> asyncClosingFunction4, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) {
                final /* synthetic */ Combiner4 this$0;

                {
                    this.this$0 = r1;
                }

                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction4.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4));
                }

                public String toString() {
                    return asyncClosingFunction4.toString();
                }
            }, executor);
        }
    }

    public static final class Combiner5<V1, V2, V3, V4, V5> extends Combiner {
        /* access modifiers changed from: private */
        public final ClosingFuture<V1> future1;
        /* access modifiers changed from: private */
        public final ClosingFuture<V2> future2;
        /* access modifiers changed from: private */
        public final ClosingFuture<V3> future3;
        /* access modifiers changed from: private */
        public final ClosingFuture<V4> future4;
        /* access modifiers changed from: private */
        public final ClosingFuture<V5> future5;

        public interface AsyncClosingFunction5<V1, V2, V3, V4, V5, U> {
            ClosingFuture<U> apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4, @ParametricNullness V5 v5) throws Exception;
        }

        public interface ClosingFunction5<V1, V2, V3, V4, V5, U> {
            @ParametricNullness
            U apply(DeferredCloser deferredCloser, @ParametricNullness V1 v1, @ParametricNullness V2 v2, @ParametricNullness V3 v3, @ParametricNullness V4 v4, @ParametricNullness V5 v5) throws Exception;
        }

        private Combiner5(ClosingFuture<V1> closingFuture, ClosingFuture<V2> closingFuture2, ClosingFuture<V3> closingFuture3, ClosingFuture<V4> closingFuture4, ClosingFuture<V5> closingFuture5) {
            super(true, ImmutableList.of(closingFuture, closingFuture2, closingFuture3, closingFuture4, closingFuture5));
            this.future1 = closingFuture;
            this.future2 = closingFuture2;
            this.future3 = closingFuture3;
            this.future4 = closingFuture4;
            this.future5 = closingFuture5;
        }

        public <U> ClosingFuture<U> call(final ClosingFunction5<V1, V2, V3, V4, V5, U> closingFunction5, Executor executor) {
            return call(new Combiner.CombiningCallable<U>(this) {
                final /* synthetic */ Combiner5 this$0;

                {
                    this.this$0 = r1;
                }

                @ParametricNullness
                public U call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return closingFunction5.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4), peeker.getDone(this.this$0.future5));
                }

                public String toString() {
                    return closingFunction5.toString();
                }
            }, executor);
        }

        public <U> ClosingFuture<U> callAsync(final AsyncClosingFunction5<V1, V2, V3, V4, V5, U> asyncClosingFunction5, Executor executor) {
            return callAsync(new Combiner.AsyncCombiningCallable<U>(this) {
                final /* synthetic */ Combiner5 this$0;

                {
                    this.this$0 = r1;
                }

                public ClosingFuture<U> call(DeferredCloser deferredCloser, Peeker peeker) throws Exception {
                    return asyncClosingFunction5.apply(deferredCloser, peeker.getDone(this.this$0.future1), peeker.getDone(this.this$0.future2), peeker.getDone(this.this$0.future3), peeker.getDone(this.this$0.future4), peeker.getDone(this.this$0.future5));
                }

                public String toString() {
                    return asyncClosingFunction5.toString();
                }
            }, executor);
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("state", (Object) this.state.get()).addValue((Object) this.future).toString();
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (this.state.get().equals(State.OPEN)) {
            logger.get().log(Level.SEVERE, "Uh oh! An open ClosingFuture has leaked and will close: {0}", this);
            finishToFuture();
        }
    }

    /* access modifiers changed from: private */
    public static void closeQuietly(@CheckForNull AutoCloseable autoCloseable, Executor executor) {
        if (autoCloseable != null) {
            try {
                executor.execute(new ClosingFuture$$ExternalSyntheticLambda0(autoCloseable));
            } catch (RejectedExecutionException e) {
                LazyLogger lazyLogger = logger;
                if (lazyLogger.get().isLoggable(Level.WARNING)) {
                    lazyLogger.get().log(Level.WARNING, String.format("while submitting close to %s; will close inline", new Object[]{executor}), e);
                }
                closeQuietly(autoCloseable, MoreExecutors.directExecutor());
            }
        }
    }

    static /* synthetic */ void lambda$closeQuietly$0(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            Platform.restoreInterruptIfIsInterruptedException(e);
            logger.get().log(Level.WARNING, "thrown by close()", e);
        }
    }

    /* access modifiers changed from: private */
    public void checkAndUpdateState(State state2, State state3) {
        Preconditions.checkState(compareAndUpdateState(state2, state3), "Expected state to be %s, but it was %s", (Object) state2, (Object) state3);
    }

    private boolean compareAndUpdateState(State state2, State state3) {
        return PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.state, state2, state3);
    }

    private static final class CloseableList extends IdentityHashMap<AutoCloseable, Executor> implements Closeable {
        private volatile boolean closed;
        /* access modifiers changed from: private */
        public final DeferredCloser closer;
        @CheckForNull
        private volatile CountDownLatch whenClosed;

        private CloseableList() {
            this.closer = new DeferredCloser(this);
        }

        /* access modifiers changed from: package-private */
        public <V, U> ListenableFuture<U> applyClosingFunction(ClosingFunction<? super V, U> closingFunction, @ParametricNullness V v) throws Exception {
            CloseableList closeableList = new CloseableList();
            try {
                return Futures.immediateFuture(closingFunction.apply(closeableList.closer, v));
            } finally {
                add(closeableList, MoreExecutors.directExecutor());
            }
        }

        /* access modifiers changed from: package-private */
        public <V, U> FluentFuture<U> applyAsyncClosingFunction(AsyncClosingFunction<V, U> asyncClosingFunction, @ParametricNullness V v) throws Exception {
            CloseableList closeableList = new CloseableList();
            try {
                ClosingFuture<U> apply = asyncClosingFunction.apply(closeableList.closer, v);
                apply.becomeSubsumedInto(closeableList);
                return apply.future;
            } finally {
                add(closeableList, MoreExecutors.directExecutor());
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
            r0 = entrySet().iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
            if (r0.hasNext() == false) goto L_0x0034;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
            r1 = (java.util.Map.Entry) r0.next();
            com.google.common.util.concurrent.ClosingFuture.access$3200((java.lang.AutoCloseable) r1.getKey(), (java.util.concurrent.Executor) r1.getValue());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
            clear();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
            if (r3.whenClosed == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
            r3.whenClosed.countDown();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() {
            /*
                r3 = this;
                boolean r0 = r3.closed
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r3)
                boolean r0 = r3.closed     // Catch:{ all -> 0x0041 }
                if (r0 == 0) goto L_0x000c
                monitor-exit(r3)     // Catch:{ all -> 0x0041 }
                return
            L_0x000c:
                r0 = 1
                r3.closed = r0     // Catch:{ all -> 0x0041 }
                monitor-exit(r3)     // Catch:{ all -> 0x0041 }
                java.util.Set r0 = r3.entrySet()
                java.util.Iterator r0 = r0.iterator()
            L_0x0018:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0034
                java.lang.Object r1 = r0.next()
                java.util.Map$Entry r1 = (java.util.Map.Entry) r1
                java.lang.Object r2 = r1.getKey()
                java.lang.AutoCloseable r2 = (java.lang.AutoCloseable) r2
                java.lang.Object r1 = r1.getValue()
                java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
                com.google.common.util.concurrent.ClosingFuture.closeQuietly(r2, r1)
                goto L_0x0018
            L_0x0034:
                r3.clear()
                java.util.concurrent.CountDownLatch r0 = r3.whenClosed
                if (r0 == 0) goto L_0x0040
                java.util.concurrent.CountDownLatch r0 = r3.whenClosed
                r0.countDown()
            L_0x0040:
                return
            L_0x0041:
                r0 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0041 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ClosingFuture.CloseableList.close():void");
        }

        /* access modifiers changed from: package-private */
        public void add(@CheckForNull AutoCloseable autoCloseable, Executor executor) {
            Preconditions.checkNotNull(executor);
            if (autoCloseable != null) {
                synchronized (this) {
                    if (!this.closed) {
                        put(autoCloseable, executor);
                    } else {
                        ClosingFuture.closeQuietly(autoCloseable, executor);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public CountDownLatch whenClosedCountDown() {
            boolean z = false;
            if (this.closed) {
                return new CountDownLatch(0);
            }
            synchronized (this) {
                if (this.closed) {
                    CountDownLatch countDownLatch = new CountDownLatch(0);
                    return countDownLatch;
                }
                if (this.whenClosed == null) {
                    z = true;
                }
                Preconditions.checkState(z);
                CountDownLatch countDownLatch2 = new CountDownLatch(1);
                this.whenClosed = countDownLatch2;
                return countDownLatch2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CountDownLatch whenClosedCountDown() {
        return this.closeables.whenClosedCountDown();
    }
}
