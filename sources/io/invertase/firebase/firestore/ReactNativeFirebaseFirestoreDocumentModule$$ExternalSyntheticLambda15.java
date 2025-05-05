package io.invertase.firebase.firestore;

import com.google.firebase.firestore.DocumentReference;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda15 implements Callable {
    public final /* synthetic */ DocumentReference f$0;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda15(DocumentReference documentReference) {
        this.f$0 = documentReference;
    }

    public final Object call() {
        return this.f$0.delete();
    }
}
