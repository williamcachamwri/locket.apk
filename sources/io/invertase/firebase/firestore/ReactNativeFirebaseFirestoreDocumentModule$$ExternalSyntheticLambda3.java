package io.invertase.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda3 implements Continuation {
    public final /* synthetic */ FirebaseFirestore f$0;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda3(FirebaseFirestore firebaseFirestore) {
        this.f$0 = firebaseFirestore;
    }

    public final Object then(Task task) {
        return ReactNativeFirebaseFirestoreDocumentModule.lambda$documentBatch$11(this.f$0, task);
    }
}
