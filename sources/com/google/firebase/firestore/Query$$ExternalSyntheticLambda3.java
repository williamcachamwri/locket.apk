package com.google.firebase.firestore;

import com.google.firebase.firestore.core.ViewSnapshot;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Query$$ExternalSyntheticLambda3 implements EventListener {
    public final /* synthetic */ Query f$0;
    public final /* synthetic */ EventListener f$1;

    public /* synthetic */ Query$$ExternalSyntheticLambda3(Query query, EventListener eventListener) {
        this.f$0 = query;
        this.f$1 = eventListener;
    }

    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        this.f$0.m638lambda$addSnapshotListenerInternal$3$comgooglefirebasefirestoreQuery(this.f$1, (ViewSnapshot) obj, firebaseFirestoreException);
    }
}
