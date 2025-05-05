package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AggregateFuture;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
final class CombinedFuture<V> extends AggregateFuture<Object, V> {
    /* access modifiers changed from: private */
    @CheckForNull
    @LazyInit
    public CombinedFuture<V>.CombinedFutureInterruptibleTask<?> task;

    /* access modifiers changed from: package-private */
    public void collectOneValue(int i, @CheckForNull Object obj) {
    }

    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, AsyncCallable<V> asyncCallable) {
        super(immutableCollection, z, false);
        this.task = new AsyncCallableInterruptibleTask(asyncCallable, executor);
        init();
    }

    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, Callable<V> callable) {
        super(immutableCollection, z, false);
        this.task = new CallableInterruptibleTask(callable, executor);
        init();
    }

    /* access modifiers changed from: package-private */
    public void handleAllCompleted() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.execute();
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseResources(AggregateFuture.ReleaseResourcesReason releaseResourcesReason) {
        super.releaseResources(releaseResourcesReason);
        if (releaseResourcesReason == AggregateFuture.ReleaseResourcesReason.OUTPUT_FUTURE_DONE) {
            this.task = null;
        }
    }

    /* access modifiers changed from: protected */
    public void interruptTask() {
        CombinedFuture<V>.CombinedFutureInterruptibleTask<?> combinedFutureInterruptibleTask = this.task;
        if (combinedFutureInterruptibleTask != null) {
            combinedFutureInterruptibleTask.interruptTask();
        }
    }

    private abstract class CombinedFutureInterruptibleTask<T> extends InterruptibleTask<T> {
        private final Executor listenerExecutor;

        /* access modifiers changed from: package-private */
        public abstract void setValue(@ParametricNullness T t);

        CombinedFutureInterruptibleTask(Executor executor) {
            this.listenerExecutor = (Executor) Preconditions.checkNotNull(executor);
        }

        /* access modifiers changed from: package-private */
        public final boolean isDone() {
            return CombinedFuture.this.isDone();
        }

        /* access modifiers changed from: package-private */
        public final void execute() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e) {
                CombinedFuture.this.setException(e);
            }
        }

        /* access modifiers changed from: package-private */
        public final void afterRanInterruptiblySuccess(@ParametricNullness T t) {
            CombinedFutureInterruptibleTask unused = CombinedFuture.this.task = null;
            setValue(t);
        }

        /* access modifiers changed from: package-private */
        public final void afterRanInterruptiblyFailure(Throwable th) {
            CombinedFutureInterruptibleTask unused = CombinedFuture.this.task = null;
            if (th instanceof ExecutionException) {
                CombinedFuture.this.setException(((ExecutionException) th).getCause());
            } else if (th instanceof CancellationException) {
                CombinedFuture.this.cancel(false);
            } else {
                CombinedFuture.this.setException(th);
            }
        }
    }

    private final class AsyncCallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;

        AsyncCallableInterruptibleTask(AsyncCallable<V> asyncCallable, Executor executor) {
            super(executor);
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<V> runInterruptibly() throws Exception {
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) this.callable);
        }

        /* access modifiers changed from: package-private */
        public void setValue(ListenableFuture<V> listenableFuture) {
            CombinedFuture.this.setFuture(listenableFuture);
        }

        /* access modifiers changed from: package-private */
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    private final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask<V> {
        private final Callable<V> callable;

        CallableInterruptibleTask(Callable<V> callable2, Executor executor) {
            super(executor);
            this.callable = (Callable) Preconditions.checkNotNull(callable2);
        }

        /* access modifiers changed from: package-private */
        @ParametricNullness
        public V runInterruptibly() throws Exception {
            return this.callable.call();
        }

        /* access modifiers changed from: package-private */
        public void setValue(@ParametricNullness V v) {
            CombinedFuture.this.set(v);
        }

        /* access modifiers changed from: package-private */
        public String toPendingString() {
            return this.callable.toString();
        }
    }
}
