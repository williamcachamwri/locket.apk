package com.google.firebase.appcheck.internal;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appcheck.AppCheckToken;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultFirebaseAppCheck$$ExternalSyntheticLambda2 implements SuccessContinuation {
    public final /* synthetic */ DefaultFirebaseAppCheck f$0;

    public /* synthetic */ DefaultFirebaseAppCheck$$ExternalSyntheticLambda2(DefaultFirebaseAppCheck defaultFirebaseAppCheck) {
        this.f$0 = defaultFirebaseAppCheck;
    }

    public final Task then(Object obj) {
        return this.f$0.m572lambda$fetchTokenFromProvider$5$comgooglefirebaseappcheckinternalDefaultFirebaseAppCheck((AppCheckToken) obj);
    }
}
