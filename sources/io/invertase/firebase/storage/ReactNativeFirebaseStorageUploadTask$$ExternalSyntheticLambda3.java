package io.invertase.firebase.storage;

import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.UploadTask;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda3 implements OnPausedListener {
    public final /* synthetic */ ReactNativeFirebaseStorageUploadTask f$0;

    public /* synthetic */ ReactNativeFirebaseStorageUploadTask$$ExternalSyntheticLambda3(ReactNativeFirebaseStorageUploadTask reactNativeFirebaseStorageUploadTask) {
        this.f$0 = reactNativeFirebaseStorageUploadTask;
    }

    public final void onPaused(Object obj) {
        this.f$0.lambda$addEventListeners$2((UploadTask.TaskSnapshot) obj);
    }
}
