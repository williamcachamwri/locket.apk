package io.invertase.firebase.firestore;

import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ QuerySnapshot f$2;
    public final /* synthetic */ MetadataChanges f$3;

    public /* synthetic */ ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda1(String str, String str2, QuerySnapshot querySnapshot, MetadataChanges metadataChanges) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = querySnapshot;
        this.f$3 = metadataChanges;
    }

    public final Object call() {
        return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(this.f$0, this.f$1, "onSnapshot", this.f$2, this.f$3);
    }
}
