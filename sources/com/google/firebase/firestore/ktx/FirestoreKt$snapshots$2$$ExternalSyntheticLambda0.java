package com.google.firebase.firestore.ktx;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirestoreKt$snapshots$2$$ExternalSyntheticLambda0 implements EventListener {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ FirestoreKt$snapshots$2$$ExternalSyntheticLambda0(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        FirestoreKt$snapshots$2.invokeSuspend$lambda$0(this.f$0, (QuerySnapshot) obj, firebaseFirestoreException);
    }
}
