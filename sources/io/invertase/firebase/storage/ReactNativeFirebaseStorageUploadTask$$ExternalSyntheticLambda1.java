package io.invertase.firebase.storage;

import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda1 implements OnProgressListener {
    public final /* synthetic */ ReactNativeFirebaseStorageUploadTask f$0;

    public /* synthetic */ ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda1(ReactNativeFirebaseStorageUploadTask reactNativeFirebaseStorageUploadTask) {
        this.f$0 = reactNativeFirebaseStorageUploadTask;
    }

    public final void onProgress(Object obj) {
        this.f$0.lambda$addEventListeners$0((UploadTask.TaskSnapshot) obj);
    }
}
