package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CrashlyticsWorker$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ Callable f$0;

    public /* synthetic */ CrashlyticsWorker$$ExternalSyntheticLambda0(Callable callable) {
        this.f$0 = callable;
    }

    public final Object then(Task task) {
        return CrashlyticsWorker.lambda$submitTask$2(this.f$0, task);
    }
}
