package com.google.firebase.functions;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseContextProvider$$ExternalSyntheticLambda0 implements SuccessContinuation {
    public final /* synthetic */ FirebaseContextProvider f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;

    public /* synthetic */ FirebaseContextProvider$$ExternalSyntheticLambda0(FirebaseContextProvider firebaseContextProvider, Task task, Task task2) {
        this.f$0 = firebaseContextProvider;
        this.f$1 = task;
        this.f$2 = task2;
    }

    public final Task then(Object obj) {
        return this.f$0.m770lambda$getContext$2$comgooglefirebasefunctionsFirebaseContextProvider(this.f$1, this.f$2, (Void) obj);
    }
}
