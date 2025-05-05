package io.invertase.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda0 implements EventListener {
    public final /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda0(ReactNativeFirebaseFirestoreDocumentModule reactNativeFirebaseFirestoreDocumentModule, int i, String str, String str2) {
        this.f$0 = reactNativeFirebaseFirestoreDocumentModule;
        this.f$1 = i;
        this.f$2 = str;
        this.f$3 = str2;
    }

    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        this.f$0.lambda$documentOnSnapshot$0(this.f$1, this.f$2, this.f$3, (DocumentSnapshot) obj, firebaseFirestoreException);
    }
}
