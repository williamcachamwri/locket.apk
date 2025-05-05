package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ FirestoreClient f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ TaskCompletionSource f$2;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda10(FirestoreClient firestoreClient, List list, TaskCompletionSource taskCompletionSource) {
        this.f$0 = firestoreClient;
        this.f$1 = list;
        this.f$2 = taskCompletionSource;
    }

    public final void run() {
        this.f$0.m670lambda$write$12$comgooglefirebasefirestorecoreFirestoreClient(this.f$1, this.f$2);
    }
}
