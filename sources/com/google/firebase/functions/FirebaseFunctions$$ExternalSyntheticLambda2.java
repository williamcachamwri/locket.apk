package com.google.firebase.functions;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseFunctions$$ExternalSyntheticLambda2 implements Continuation {
    public final /* synthetic */ FirebaseFunctions f$0;
    public final /* synthetic */ HttpsCallOptions f$1;

    public /* synthetic */ FirebaseFunctions$$ExternalSyntheticLambda2(FirebaseFunctions firebaseFunctions, HttpsCallOptions httpsCallOptions) {
        this.f$0 = firebaseFunctions;
        this.f$1 = httpsCallOptions;
    }

    public final Object then(Task task) {
        return this.f$0.m774lambda$call$3$comgooglefirebasefunctionsFirebaseFunctions(this.f$1, task);
    }
}
