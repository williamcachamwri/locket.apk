package com.google.firebase.firestore.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

public class BackgroundQueue implements Executor {
    private Semaphore completedTasks = new Semaphore(0);
    private int pendingTaskCount = 0;

    public void execute(Runnable runnable) {
        this.pendingTaskCount++;
        Executors.BACKGROUND_EXECUTOR.execute(new BackgroundQueue$$ExternalSyntheticLambda0(this, runnable));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$execute$0$com-google-firebase-firestore-util-BackgroundQueue  reason: not valid java name */
    public /* synthetic */ void m766lambda$execute$0$comgooglefirebasefirestoreutilBackgroundQueue(Runnable runnable) {
        runnable.run();
        this.completedTasks.release();
    }

    public void drain() {
        try {
            this.completedTasks.acquire(this.pendingTaskCount);
            this.pendingTaskCount = 0;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Assert.fail("Interrupted while waiting for background task", e);
        }
    }
}
