package io.invertase.firebase.firestore;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Transaction;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda0 implements Transaction.Function {
    public final /* synthetic */ ReactNativeFirebaseFirestoreTransactionHandler f$0;
    public final /* synthetic */ ReactNativeFirebaseEventEmitter f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ FirebaseFirestore f$3;

    public /* synthetic */ ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda0(ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler, ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, String str, FirebaseFirestore firebaseFirestore) {
        this.f$0 = reactNativeFirebaseFirestoreTransactionHandler;
        this.f$1 = reactNativeFirebaseEventEmitter;
        this.f$2 = str;
        this.f$3 = firebaseFirestore;
    }

    public final Object apply(Transaction transaction) {
        return ReactNativeFirebaseFirestoreTransactionModule.lambda$transactionBegin$3(this.f$0, this.f$1, this.f$2, this.f$3, transaction);
    }
}
