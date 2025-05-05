package com.google.firebase.iid;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.Store;

/* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
final /* synthetic */ class FirebaseInstanceId$$Lambda$6 implements OnSuccessListener {
    private final FirebaseInstanceId arg$1;
    private final Store.Token arg$2;

    FirebaseInstanceId$$Lambda$6(FirebaseInstanceId firebaseInstanceId, Store.Token token) {
        this.arg$1 = firebaseInstanceId;
        this.arg$2 = token;
    }

    public void onSuccess(Object obj) {
        this.arg$1.lambda$getInstanceId$1$FirebaseInstanceId(this.arg$2, (InstanceIdResult) obj);
    }
}
