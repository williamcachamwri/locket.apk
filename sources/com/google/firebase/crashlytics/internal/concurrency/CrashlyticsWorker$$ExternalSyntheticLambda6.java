package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CrashlyticsWorker$$ExternalSyntheticLambda6 implements Continuation {
    public final /* synthetic */ Callable f$0;

    public /* synthetic */ CrashlyticsWorker$$ExternalSyntheticLambda6(Callable callable) {
        this.f$0 = callable;
    }

    public final Object then(Task task) {
        return Tasks.forResult(this.f$0.call());
    }
}
