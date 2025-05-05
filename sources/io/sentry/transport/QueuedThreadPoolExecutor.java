package io.sentry.transport;

import io.sentry.ILogger;
import io.sentry.SentryLevel;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class QueuedThreadPoolExecutor extends ThreadPoolExecutor {
    private final ILogger logger;
    private final int maxQueueSize;
    private final ReusableCountLatch unfinishedTasksCount = new ReusableCountLatch();

    public QueuedThreadPoolExecutor(int i, int i2, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler, ILogger iLogger) {
        super(i, i, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory, rejectedExecutionHandler);
        this.maxQueueSize = i2;
        this.logger = iLogger;
    }

    public Future<?> submit(Runnable runnable) {
        if (isSchedulingAllowed()) {
            this.unfinishedTasksCount.increment();
            return super.submit(runnable);
        }
        this.logger.log(SentryLevel.WARNING, "Submit cancelled", new Object[0]);
        return new CancelledFuture();
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        try {
            super.afterExecute(runnable, th);
        } finally {
            this.unfinishedTasksCount.decrement();
        }
    }

    /* access modifiers changed from: package-private */
    public void waitTillIdle(long j) {
        try {
            this.unfinishedTasksCount.waitTillZero(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            this.logger.log(SentryLevel.ERROR, "Failed to wait till idle", (Throwable) e);
            Thread.currentThread().interrupt();
        }
    }

    private boolean isSchedulingAllowed() {
        return this.unfinishedTasksCount.getCount() < this.maxQueueSize;
    }

    static final class CancelledFuture<T> implements Future<T> {
        public boolean cancel(boolean z) {
            return true;
        }

        public boolean isCancelled() {
            return true;
        }

        public boolean isDone() {
            return true;
        }

        CancelledFuture() {
        }

        public T get() {
            throw new CancellationException();
        }

        public T get(long j, TimeUnit timeUnit) {
            throw new CancellationException();
        }
    }
}
