package com.google.firebase.firestore.core;

import com.google.firebase.firestore.TransactionOptions;
import com.google.firebase.firestore.util.Function;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda5 implements Callable {
    public final /* synthetic */ FirestoreClient f$0;
    public final /* synthetic */ TransactionOptions f$1;
    public final /* synthetic */ Function f$2;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda5(FirestoreClient firestoreClient, TransactionOptions transactionOptions, Function function) {
        this.f$0 = firestoreClient;
        this.f$1 = transactionOptions;
        this.f$2 = function;
    }

    public final Object call() {
        return this.f$0.m668lambda$transaction$13$comgooglefirebasefirestorecoreFirestoreClient(this.f$1, this.f$2);
    }
}
