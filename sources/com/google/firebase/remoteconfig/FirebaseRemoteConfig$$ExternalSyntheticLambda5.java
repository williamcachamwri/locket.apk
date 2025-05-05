package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseRemoteConfig$$ExternalSyntheticLambda5 implements Continuation {
    public final /* synthetic */ Task f$0;

    public /* synthetic */ FirebaseRemoteConfig$$ExternalSyntheticLambda5(Task task) {
        this.f$0 = task;
    }

    public final Object then(Task task) {
        return FirebaseRemoteConfig.lambda$ensureInitialized$0(this.f$0, task);
    }
}
