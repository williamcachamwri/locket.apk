package io.invertase.firebase.firestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda8 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ ReactNativeFirebaseFirestoreDocumentModule$$ExternalSyntheticLambda8(ReactNativeFirebaseFirestoreDocumentModule reactNativeFirebaseFirestoreDocumentModule, String str, String str2, int i) {
        this.f$0 = reactNativeFirebaseFirestoreDocumentModule;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = i;
    }

    public final void onComplete(Task task) {
        this.f$0.lambda$sendOnSnapshotEvent$14(this.f$1, this.f$2, this.f$3, task);
    }
}
