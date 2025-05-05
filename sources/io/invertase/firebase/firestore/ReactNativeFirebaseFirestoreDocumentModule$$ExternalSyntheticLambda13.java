package io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda13 implements Continuation {
    public final /* synthetic */ ReadableMap f$0;
    public final /* synthetic */ DocumentReference f$1;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda13(ReadableMap readableMap, DocumentReference documentReference) {
        this.f$0 = readableMap;
        this.f$1 = documentReference;
    }

    public final Object then(Task task) {
        return ReactNativeFirebaseFirestoreDocumentModule.lambda$documentSet$5(this.f$0, this.f$1, task);
    }
}
