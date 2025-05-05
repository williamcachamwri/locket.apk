package io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableArray;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ FirebaseFirestore f$0;
    public final /* synthetic */ ReadableArray f$1;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda2(FirebaseFirestore firebaseFirestore, ReadableArray readableArray) {
        this.f$0 = firebaseFirestore;
        this.f$1 = readableArray;
    }

    public final Object call() {
        return ReactNativeFirebaseFirestoreSerialize.parseDocumentBatches(this.f$0, this.f$1);
    }
}
