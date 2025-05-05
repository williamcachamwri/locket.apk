package io.invertase.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda7 implements Callable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ DocumentSnapshot f$2;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda7(String str, String str2, DocumentSnapshot documentSnapshot) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = documentSnapshot;
    }

    public final Object call() {
        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(this.f$0, this.f$1, this.f$2);
    }
}
