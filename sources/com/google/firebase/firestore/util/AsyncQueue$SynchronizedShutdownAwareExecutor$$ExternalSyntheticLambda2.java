package com.google.firebase.firestore.util;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.util.AsyncQueue;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ TaskCompletionSource f$0;
    public final /* synthetic */ Callable f$1;

    public /* synthetic */ AsyncQueue$SynchronizedShutdownAwareExecutor$$ExternalSyntheticLambda2(TaskCompletionSource taskCompletionSource, Callable callable) {
        this.f$0 = taskCompletionSource;
        this.f$1 = callable;
    }

    public final void run() {
        AsyncQueue.SynchronizedShutdownAwareExecutor.lambda$executeAndReportResult$1(this.f$0, this.f$1);
    }
}
