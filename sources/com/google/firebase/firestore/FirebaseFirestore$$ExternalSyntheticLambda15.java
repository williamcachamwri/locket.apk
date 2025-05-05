package com.google.firebase.firestore;

import com.google.firebase.firestore.Transaction;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseFirestore$$ExternalSyntheticLambda15 implements Callable {
    public final /* synthetic */ FirebaseFirestore f$0;
    public final /* synthetic */ Transaction.Function f$1;
    public final /* synthetic */ com.google.firebase.firestore.core.Transaction f$2;

    public /* synthetic */ FirebaseFirestore$$ExternalSyntheticLambda15(FirebaseFirestore firebaseFirestore, Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) {
        this.f$0 = firebaseFirestore;
        this.f$1 = function;
        this.f$2 = transaction;
    }

    public final Object call() {
        return this.f$0.m632lambda$runTransaction$1$comgooglefirebasefirestoreFirebaseFirestore(this.f$1, this.f$2);
    }
}
