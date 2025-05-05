package io.invertase.firebase.storage;

import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.OnProgressListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda1 implements OnProgressListener {
    public final /* synthetic */ ReactNativeFirebaseStorageDownloadTask f$0;

    public /* synthetic */ ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda1(ReactNativeFirebaseStorageDownloadTask reactNativeFirebaseStorageDownloadTask) {
        this.f$0 = reactNativeFirebaseStorageDownloadTask;
    }

    public final void onProgress(Object obj) {
        this.f$0.lambda$addEventListeners$1((FileDownloadTask.TaskSnapshot) obj);
    }
}
