package io.grpc.internal;

import io.grpc.SynchronizationContext;
import io.grpc.internal.BackoffPolicy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

final class BackoffPolicyRetryScheduler implements RetryScheduler {
    private static final Logger logger = Logger.getLogger(BackoffPolicyRetryScheduler.class.getName());
    private BackoffPolicy policy;
    private final BackoffPolicy.Provider policyProvider;
    private final ScheduledExecutorService scheduledExecutorService;
    private SynchronizationContext.ScheduledHandle scheduledHandle;
    private final SynchronizationContext syncContext;

    BackoffPolicyRetryScheduler(BackoffPolicy.Provider provider, ScheduledExecutorService scheduledExecutorService2, SynchronizationContext synchronizationContext) {
        this.policyProvider = provider;
        this.scheduledExecutorService = scheduledExecutorService2;
        this.syncContext = synchronizationContext;
    }

    public void schedule(Runnable runnable) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.policy == null) {
            this.policy = this.policyProvider.get();
        }
        SynchronizationContext.ScheduledHandle scheduledHandle2 = this.scheduledHandle;
        if (scheduledHandle2 == null || !scheduledHandle2.isPending()) {
            long nextBackoffNanos = this.policy.nextBackoffNanos();
            this.scheduledHandle = this.syncContext.schedule(runnable, nextBackoffNanos, TimeUnit.NANOSECONDS, this.scheduledExecutorService);
            logger.log(Level.FINE, "Scheduling DNS resolution backoff for {0}ns", Long.valueOf(nextBackoffNanos));
        }
    }

    public void reset() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        this.syncContext.execute(new BackoffPolicyRetryScheduler$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$reset$0$io-grpc-internal-BackoffPolicyRetryScheduler  reason: not valid java name */
    public /* synthetic */ void m2327lambda$reset$0$iogrpcinternalBackoffPolicyRetryScheduler() {
        SynchronizationContext.ScheduledHandle scheduledHandle2 = this.scheduledHandle;
        if (scheduledHandle2 != null && scheduledHandle2.isPending()) {
            this.scheduledHandle.cancel();
        }
        this.policy = null;
    }
}
