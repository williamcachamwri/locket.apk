package com.google.firebase.firestore.core;

import com.google.firebase.firestore.FirebaseFirestoreException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsyncEventListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AsyncEventListener f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ FirebaseFirestoreException f$2;

    public /* synthetic */ AsyncEventListener$$ExternalSyntheticLambda0(AsyncEventListener asyncEventListener, Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        this.f$0 = asyncEventListener;
        this.f$1 = obj;
        this.f$2 = firebaseFirestoreException;
    }

    public final void run() {
        this.f$0.m649lambda$onEvent$0$comgooglefirebasefirestorecoreAsyncEventListener(this.f$1, this.f$2);
    }
}
