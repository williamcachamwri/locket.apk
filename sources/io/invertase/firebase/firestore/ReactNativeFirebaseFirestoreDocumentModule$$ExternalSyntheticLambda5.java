package io.invertase.firebase.firestore;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Source;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda5 implements Callable {
    public final /* synthetic */ DocumentReference f$0;
    public final /* synthetic */ Source f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda5(DocumentReference documentReference, Source source, String str, String str2) {
        this.f$0 = documentReference;
        this.f$1 = source;
        this.f$2 = str;
        this.f$3 = str2;
    }

    public final Object call() {
        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(this.f$2, this.f$3, (DocumentSnapshot) Tasks.await(this.f$0.get(this.f$1)));
    }
}
