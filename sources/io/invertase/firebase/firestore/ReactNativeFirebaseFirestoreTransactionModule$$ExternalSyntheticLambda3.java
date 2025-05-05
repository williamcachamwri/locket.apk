package io.invertase.firebase.firestore;

import com.google.firebase.firestore.DocumentReference;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda3 implements Callable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ReactNativeFirebaseFirestoreTransactionHandler f$2;
    public final /* synthetic */ DocumentReference f$3;

    public /* synthetic */ ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda3(String str, String str2, ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler, DocumentReference documentReference) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = reactNativeFirebaseFirestoreTransactionHandler;
        this.f$3 = documentReference;
    }

    public final Object call() {
        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(this.f$0, this.f$1, this.f$2.getDocument(this.f$3));
    }
}
