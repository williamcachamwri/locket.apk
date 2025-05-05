package com.google.firebase.functions;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appcheck.AppCheckTokenResult;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseContextProvider$$ExternalSyntheticLambda1 implements SuccessContinuation {
    public final /* synthetic */ FirebaseContextProvider f$0;

    public /* synthetic */ FirebaseContextProvider$$ExternalSyntheticLambda1(FirebaseContextProvider firebaseContextProvider) {
        this.f$0 = firebaseContextProvider;
    }

    public final Task then(Object obj) {
        return this.f$0.m769lambda$getAppCheckToken$4$comgooglefirebasefunctionsFirebaseContextProvider((AppCheckTokenResult) obj);
    }
}
