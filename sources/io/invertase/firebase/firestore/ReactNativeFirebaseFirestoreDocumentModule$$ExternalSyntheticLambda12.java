package io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda12 implements Callable {
    public final /* synthetic */ FirebaseFirestore f$0;
    public final /* synthetic */ ReadableMap f$1;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda12(FirebaseFirestore firebaseFirestore, ReadableMap readableMap) {
        this.f$0 = firebaseFirestore;
        this.f$1 = readableMap;
    }

    public final Object call() {
        return ReactNativeFirebaseFirestoreSerialize.parseReadableMap(this.f$0, this.f$1);
    }
}
