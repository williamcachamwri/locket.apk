package io.invertase.firebase.storage;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseStorageDownloadTask f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda0(ReactNativeFirebaseStorageDownloadTask reactNativeFirebaseStorageDownloadTask, Promise promise) {
        this.f$0 = reactNativeFirebaseStorageDownloadTask;
        this.f$1 = promise;
    }

    public final void onComplete(Task task) {
        this.f$0.lambda$addOnCompleteListener$0(this.f$1, task);
    }
}
