package io.invertase.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import java.util.Map;
import java.util.Objects;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda10 implements Continuation {
    public final /* synthetic */ DocumentReference f$0;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda10(DocumentReference documentReference) {
        this.f$0 = documentReference;
    }

    public final Object then(Task task) {
        return this.f$0.update((Map<String, Object>) (Map) Objects.requireNonNull((Map) task.getResult()));
    }
}
