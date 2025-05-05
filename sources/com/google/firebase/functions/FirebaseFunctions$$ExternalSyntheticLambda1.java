package com.google.firebase.functions;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseFunctions$$ExternalSyntheticLambda1 implements Continuation {
    public final /* synthetic */ FirebaseFunctions f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Object f$2;
    public final /* synthetic */ HttpsCallOptions f$3;

    public /* synthetic */ FirebaseFunctions$$ExternalSyntheticLambda1(FirebaseFunctions firebaseFunctions, String str, Object obj, HttpsCallOptions httpsCallOptions) {
        this.f$0 = firebaseFunctions;
        this.f$1 = str;
        this.f$2 = obj;
        this.f$3 = httpsCallOptions;
    }

    public final Object then(Task task) {
        return this.f$0.m773lambda$call$2$comgooglefirebasefunctionsFirebaseFunctions(this.f$1, this.f$2, this.f$3, task);
    }
}
