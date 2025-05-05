package io.invertase.firebase.firestore;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda5 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseFirestoreModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ ReactNativeFirebaseFirestoreModule$$ExternalSyntheticLambda5(ReactNativeFirebaseFirestoreModule reactNativeFirebaseFirestoreModule, Promise promise) {
        this.f$0 = reactNativeFirebaseFirestoreModule;
        this.f$1 = promise;
    }

    public final void onComplete(Task task) {
        this.f$0.lambda$loadBundle$0(this.f$1, task);
    }
}
