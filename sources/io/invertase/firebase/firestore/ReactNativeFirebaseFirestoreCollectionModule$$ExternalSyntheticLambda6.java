package io.invertase.firebase.firestore;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.QuerySnapshot;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda6 implements EventListener {
    public final /* synthetic */ ReactNativeFirebaseFirestoreCollectionModule f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ MetadataChanges f$4;

    public /* synthetic */ ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda6(ReactNativeFirebaseFirestoreCollectionModule reactNativeFirebaseFirestoreCollectionModule, int i, String str, String str2, MetadataChanges metadataChanges) {
        this.f$0 = reactNativeFirebaseFirestoreCollectionModule;
        this.f$1 = i;
        this.f$2 = str;
        this.f$3 = str2;
        this.f$4 = metadataChanges;
    }

    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
        this.f$0.lambda$handleQueryOnSnapshot$4(this.f$1, this.f$2, this.f$3, this.f$4, (QuerySnapshot) obj, firebaseFirestoreException);
    }
}
