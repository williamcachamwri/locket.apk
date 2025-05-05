package io.invertase.firebase.storage;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseStorageUploadTask f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda0(ReactNativeFirebaseStorageUploadTask reactNativeFirebaseStorageUploadTask, Promise promise) {
        this.f$0 = reactNativeFirebaseStorageUploadTask;
        this.f$1 = promise;
    }

    public final void onComplete(Task task) {
        this.f$0.lambda$addOnCompleteListener$3(this.f$1, task);
    }
}
