package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class AggregateFuture<InputT, OutputT> extends AggregateFutureState<OutputT> {
    private static final LazyLogger logger = new LazyLogger(AggregateFuture.class);
    private final boolean allMustSucceed;
    private final boolean collectsValues;
    @CheckForNull
    @LazyInit
    private ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

    enum ReleaseResourcesReason {
        OUTPUT_FUTURE_DONE,
        ALL_INPUT_FUTURES_PROCESSED
    }

    /* access modifiers changed from: package-private */
    public abstract void collectOneValue(int i, @ParametricNullness InputT inputt);

    /* access modifiers changed from: package-private */
    public abstract void handleAllCompleted();

    AggregateFuture(ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection, boolean z, boolean z2) {
        super(immutableCollection.size());
        this.futures = (ImmutableCollection) Preconditions.checkNotNull(immutableCollection);
        this.allMustSucceed = z;
        this.collectsValues = z2;
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        super.afterDone();
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.futures;
        releaseResources(ReleaseResourcesReason.OUTPUT_FUTURE_DONE);
        if (isCancelled() && (immutableCollection != null)) {
            boolean wasInterrupted = wasInterrupted();
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = immutableCollection.iterator();
            while (it.hasNext()) {
                ((Future) it.next()).cancel(wasInterrupted);
            }
        }
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String pendingToString() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.futures;
        if (immutableCollection != null) {
            return "futures=" + immutableCollection;
        }
        return super.pendingToString();
    }

    /* access modifiers changed from: package-private */
    public final void init() {
        Objects.requireNonNull(this.futures);
        if (this.futures.isEmpty()) {
            handleAllCompleted();
        } else if (this.allMustSucceed) {
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
            int i = 0;
            while (it.hasNext()) {
                ListenableFuture listenableFuture = (ListenableFuture) it.next();
                int i2 = i + 1;
                if (listenableFuture.isDone()) {
                    m564lambda$init$0$comgooglecommonutilconcurrentAggregateFuture(i, listenableFuture);
                } else {
                    listenableFuture.addListener(new AggregateFuture$$ExternalSyntheticLambda0(this, i, listenableFuture), MoreExecutors.directExecutor());
                }
                i = i2;
            }
        } else {
            ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection = this.collectsValues ? this.futures : null;
            AggregateFuture$$ExternalSyntheticLambda1 aggregateFuture$$ExternalSyntheticLambda1 = new AggregateFuture$$ExternalSyntheticLambda1(this, immutableCollection);
            UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = this.futures.iterator();
            while (it2.hasNext()) {
                ListenableFuture listenableFuture2 = (ListenableFuture) it2.next();
                if (listenableFuture2.isDone()) {
                    m565lambda$init$1$comgooglecommonutilconcurrentAggregateFuture(immutableCollection);
                } else {
                    listenableFuture2.addListener(aggregateFuture$$ExternalSyntheticLambda1, MoreExecutors.directExecutor());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: processAllMustSucceedDoneFuture */
    public void m564lambda$init$0$comgooglecommonutilconcurrentAggregateFuture(int i, ListenableFuture<? extends InputT> listenableFuture) {
        try {
            if (listenableFuture.isCancelled()) {
                this.futures = null;
                cancel(false);
            } else {
                collectValueFromNonCancelledFuture(i, listenableFuture);
            }
        } finally {
            m565lambda$init$1$comgooglecommonutilconcurrentAggregateFuture((ImmutableCollection) null);
        }
    }

    private void handleException(Throwable th) {
        Preconditions.checkNotNull(th);
        if (this.allMustSucceed && !setException(th) && addCausalChain(getOrInitSeenExceptions(), th)) {
            log(th);
        } else if (th instanceof Error) {
            log(th);
        }
    }

    private static void log(Throwable th) {
        logger.get().log(Level.SEVERE, th instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first", th);
    }

    /* access modifiers changed from: package-private */
    public final void addInitialException(Set<Throwable> set) {
        Preconditions.checkNotNull(set);
        if (!isCancelled()) {
            addCausalChain(set, (Throwable) Objects.requireNonNull(tryInternalFastPathGetFailure()));
        }
    }

    private void collectValueFromNonCancelledFuture(int i, Future<? extends InputT> future) {
        try {
            collectOneValue(i, Uninterruptibles.getUninterruptibly(future));
        } catch (ExecutionException e) {
            handleException(e.getCause());
        } catch (Throwable th) {
            handleException(th);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: decrementCountAndMaybeComplete */
    public void m565lambda$init$1$comgooglecommonutilconcurrentAggregateFuture(@CheckForNull ImmutableCollection<? extends Future<? extends InputT>> immutableCollection) {
        int decrementRemainingAndGet = decrementRemainingAndGet();
        Preconditions.checkState(decrementRemainingAndGet >= 0, "Less than 0 remaining futures");
        if (decrementRemainingAndGet == 0) {
            processCompleted(immutableCollection);
        }
    }

    private void processCompleted(@CheckForNull ImmutableCollection<? extends Future<? extends InputT>> immutableCollection) {
        if (immutableCollection != null) {
            UnmodifiableIterator<? extends Future<? extends InputT>> it = immutableCollection.iterator();
            int i = 0;
            while (it.hasNext()) {
                Future future = (Future) it.next();
                if (!future.isCancelled()) {
                    collectValueFromNonCancelledFuture(i, future);
                }
                i++;
            }
        }
        clearSeenExceptions();
        handleAllCompleted();
        releaseResources(ReleaseResourcesReason.ALL_INPUT_FUTURES_PROCESSED);
    }

    /* access modifiers changed from: package-private */
    public void releaseResources(ReleaseResourcesReason releaseResourcesReason) {
        Preconditions.checkNotNull(releaseResourcesReason);
        this.futures = null;
    }

    private static boolean addCausalChain(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }
}
