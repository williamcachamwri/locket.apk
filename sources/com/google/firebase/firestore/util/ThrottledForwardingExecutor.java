package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

class ThrottledForwardingExecutor implements Executor {
    private final Semaphore availableSlots;
    private final Executor executor;

    ThrottledForwardingExecutor(int i, Executor executor2) {
        this.availableSlots = new Semaphore(i);
        this.executor = executor2;
    }

    public void execute(Runnable runnable) {
        if (this.availableSlots.tryAcquire()) {
            try {
                this.executor.execute(new ThrottledForwardingExecutor$$ExternalSyntheticLambda0(this, runnable));
            } catch (RejectedExecutionException unused) {
                runnable.run();
            }
        } else {
            runnable.run();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$execute$0$com-google-firebase-firestore-util-ThrottledForwardingExecutor  reason: not valid java name */
    public /* synthetic */ void m768lambda$execute$0$comgooglefirebasefirestoreutilThrottledForwardingExecutor(Runnable runnable) {
        runnable.run();
        this.availableSlots.release();
    }
}
