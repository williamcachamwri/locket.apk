package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.Store;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseMessaging$$ExternalSyntheticLambda12 implements SuccessContinuation {
    public final /* synthetic */ FirebaseMessaging f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Store.Token f$2;

    public /* synthetic */ FirebaseMessaging$$ExternalSyntheticLambda12(FirebaseMessaging firebaseMessaging, String str, Store.Token token) {
        this.f$0 = firebaseMessaging;
        this.f$1 = str;
        this.f$2 = token;
    }

    public final Task then(Object obj) {
        return this.f$0.m784lambda$blockingGetToken$13$comgooglefirebasemessagingFirebaseMessaging(this.f$1, this.f$2, (String) obj);
    }
}
