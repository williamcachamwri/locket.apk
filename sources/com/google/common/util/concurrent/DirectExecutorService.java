package com.google.common.util.concurrent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

@ElementTypesAreNonnullByDefault
final class DirectExecutorService extends AbstractListeningExecutorService {
    private final Object lock = new Object();
    private int runningTasks = 0;
    private boolean shutdown = false;

    DirectExecutorService() {
    }

    public void execute(Runnable runnable) {
        startTask();
        try {
            runnable.run();
        } finally {
            endTask();
        }
    }

    public boolean isShutdown() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown;
        }
        return z;
    }

    public void shutdown() {
        synchronized (this.lock) {
            this.shutdown = true;
            if (this.runningTasks == 0) {
                this.lock.notifyAll();
            }
        }
    }

    public List<Runnable> shutdownNow() {
        shutdown();
        return Collections.emptyList();
    }

    public boolean isTerminated() {
        boolean z;
        synchronized (this.lock) {
            z = this.shutdown && this.runningTasks == 0;
        }
        return z;
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        synchronized (this.lock) {
            while (true) {
                if (this.shutdown && this.runningTasks == 0) {
                    return true;
                }
                if (nanos <= 0) {
                    return false;
                }
                long nanoTime = System.nanoTime();
                TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
                nanos -= System.nanoTime() - nanoTime;
            }
        }
    }

    private void startTask() {
        synchronized (this.lock) {
            if (!this.shutdown) {
                this.runningTasks++;
            } else {
                throw new RejectedExecutionException("Executor already shutdown");
            }
        }
    }

    private void endTask() {
        synchronized (this.lock) {
            int i = this.runningTasks - 1;
            this.runningTasks = i;
            if (i == 0) {
                this.lock.notifyAll();
            }
        }
    }
}
