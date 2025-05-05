package com.google.firebase.appcheck.debug.internal;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DebugAppCheckProvider$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FirebaseApp f$0;
    public final /* synthetic */ TaskCompletionSource f$1;

    public /* synthetic */ DebugAppCheckProvider$$ExternalSyntheticLambda1(FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        this.f$0 = firebaseApp;
        this.f$1 = taskCompletionSource;
    }

    public final void run() {
        DebugAppCheckProvider.lambda$determineDebugSecret$0(this.f$0, this.f$1);
    }
}
