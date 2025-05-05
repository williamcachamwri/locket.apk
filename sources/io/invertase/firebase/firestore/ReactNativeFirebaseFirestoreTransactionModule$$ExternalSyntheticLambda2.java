package io.invertase.firebase.firestore;

import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ReactNativeFirebaseEventEmitter f$0;
    public final /* synthetic */ ReactNativeFirebaseFirestoreTransactionHandler f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda2(ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler, String str) {
        this.f$0 = reactNativeFirebaseEventEmitter;
        this.f$1 = reactNativeFirebaseFirestoreTransactionHandler;
        this.f$2 = str;
    }

    public final void run() {
        ReactNativeFirebaseFirestoreTransactionModule.lambda$transactionBegin$2(this.f$0, this.f$1, this.f$2);
    }
}
