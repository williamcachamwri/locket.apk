package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE = new DoNothingRunnable();
    private static final int MAX_BUSY_WAIT_SPINS = 1000;
    private static final Runnable PARKED = new DoNothingRunnable();

    /* access modifiers changed from: package-private */
    public abstract void afterRanInterruptiblyFailure(Throwable th);

    /* access modifiers changed from: package-private */
    public abstract void afterRanInterruptiblySuccess(@ParametricNullness T t);

    /* access modifiers changed from: package-private */
    public abstract boolean isDone();

    /* access modifiers changed from: package-private */
    @ParametricNullness
    public abstract T runInterruptibly() throws Exception;

    /* access modifiers changed from: package-private */
    public abstract String toPendingString();

    InterruptibleTask() {
    }

    static {
        Class<LockSupport> cls = LockSupport.class;
    }

    private static final class DoNothingRunnable implements Runnable {
        public void run() {
        }

        private DoNothingRunnable() {
        }
    }

    public final void run() {
        Thread currentThread = Thread.currentThread();
        Object obj = null;
        if (compareAndSet((Object) null, currentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    obj = runInterruptibly();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, DONE)) {
                        waitForInterrupt(currentThread);
                    }
                    if (z) {
                        afterRanInterruptiblySuccess(NullnessCasts.uncheckedCastNullableTToT(null));
                    }
                    throw th;
                }
            }
            if (!compareAndSet(currentThread, DONE)) {
                waitForInterrupt(currentThread);
            }
            if (z) {
                afterRanInterruptiblySuccess(NullnessCasts.uncheckedCastNullableTToT(obj));
            }
        }
    }

    private void waitForInterrupt(Thread thread) {
        Runnable runnable = (Runnable) get();
        Blocker blocker = null;
        boolean z = false;
        int i = 0;
        while (true) {
            boolean z2 = runnable instanceof Blocker;
            if (!z2 && runnable != PARKED) {
                break;
            }
            if (z2) {
                blocker = (Blocker) runnable;
            }
            i++;
            if (i > 1000) {
                Runnable runnable2 = PARKED;
                if (runnable == runnable2 || compareAndSet(runnable, runnable2)) {
                    z = Thread.interrupted() || z;
                    LockSupport.park(blocker);
                }
            } else {
                Thread.yield();
            }
            runnable = (Runnable) get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    /* access modifiers changed from: package-private */
    public final void interruptTask() {
        Runnable runnable = (Runnable) get();
        if (runnable instanceof Thread) {
            Blocker blocker = new Blocker();
            blocker.setOwner(Thread.currentThread());
            if (compareAndSet(runnable, blocker)) {
                try {
                    ((Thread) runnable).interrupt();
                } finally {
                    if (((Runnable) getAndSet(DONE)) == PARKED) {
                        LockSupport.unpark((Thread) runnable);
                    }
                }
            }
        }
    }

    static final class Blocker extends AbstractOwnableSynchronizer implements Runnable {
        private final InterruptibleTask<?> task;

        public void run() {
        }

        private Blocker(InterruptibleTask<?> interruptibleTask) {
            this.task = interruptibleTask;
        }

        /* access modifiers changed from: private */
        public void setOwner(Thread thread) {
            super.setExclusiveOwnerThread(thread);
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public Thread getOwner() {
            return super.getExclusiveOwnerThread();
        }

        public String toString() {
            return this.task.toString();
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable instanceof Blocker) {
            str = "running=[INTERRUPTED]";
        } else {
            str = runnable instanceof Thread ? "running=[RUNNING ON " + ((Thread) runnable).getName() + "]" : "running=[NOT STARTED YET]";
        }
        return str + ", " + toPendingString();
    }
}
