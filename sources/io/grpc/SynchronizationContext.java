package io.grpc;

import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.base.Preconditions;
import java.lang.Thread;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class SynchronizationContext implements Executor {
    private final AtomicReference<Thread> drainingThread = new AtomicReference<>();
    private final Queue<Runnable> queue = new ConcurrentLinkedQueue();
    private final Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public SynchronizationContext(Thread.UncaughtExceptionHandler uncaughtExceptionHandler2) {
        this.uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler) Preconditions.checkNotNull(uncaughtExceptionHandler2, "uncaughtExceptionHandler");
    }

    public final void drain() {
        while (PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.drainingThread, (Object) null, Thread.currentThread())) {
            while (true) {
                try {
                    Runnable poll = this.queue.poll();
                    if (poll != null) {
                        poll.run();
                    } else {
                        this.drainingThread.set((Object) null);
                        if (this.queue.isEmpty()) {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    this.drainingThread.set((Object) null);
                    throw th;
                }
            }
        }
    }

    public final void executeLater(Runnable runnable) {
        this.queue.add((Runnable) Preconditions.checkNotNull(runnable, "runnable is null"));
    }

    public final void execute(Runnable runnable) {
        executeLater(runnable);
        drain();
    }

    public void throwIfNotInThisSynchronizationContext() {
        Preconditions.checkState(Thread.currentThread() == this.drainingThread.get(), "Not called from the SynchronizationContext");
    }

    public final ScheduledHandle schedule(final Runnable runnable, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        final ManagedRunnable managedRunnable = new ManagedRunnable(runnable);
        return new ScheduledHandle(managedRunnable, scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                SynchronizationContext.this.execute(managedRunnable);
            }

            public String toString() {
                return runnable.toString() + "(scheduled in SynchronizationContext)";
            }
        }, j, timeUnit));
    }

    public final ScheduledHandle scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        final Runnable runnable2 = runnable;
        ManagedRunnable managedRunnable = new ManagedRunnable(runnable);
        final ManagedRunnable managedRunnable2 = managedRunnable;
        final long j3 = j2;
        return new ScheduledHandle(managedRunnable, scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                SynchronizationContext.this.execute(managedRunnable2);
            }

            public String toString() {
                return runnable2.toString() + "(scheduled in SynchronizationContext with delay of " + j3 + ")";
            }
        }, j, j2, timeUnit));
    }

    private static class ManagedRunnable implements Runnable {
        boolean hasStarted;
        boolean isCancelled;
        final Runnable task;

        ManagedRunnable(Runnable runnable) {
            this.task = (Runnable) Preconditions.checkNotNull(runnable, "task");
        }

        public void run() {
            if (!this.isCancelled) {
                this.hasStarted = true;
                this.task.run();
            }
        }
    }

    public static final class ScheduledHandle {
        private final ScheduledFuture<?> future;
        private final ManagedRunnable runnable;

        private ScheduledHandle(ManagedRunnable managedRunnable, ScheduledFuture<?> scheduledFuture) {
            this.runnable = (ManagedRunnable) Preconditions.checkNotNull(managedRunnable, "runnable");
            this.future = (ScheduledFuture) Preconditions.checkNotNull(scheduledFuture, "future");
        }

        public void cancel() {
            this.runnable.isCancelled = true;
            this.future.cancel(false);
        }

        public boolean isPending() {
            return !this.runnable.hasStarted && !this.runnable.isCancelled;
        }
    }
}
