package io.invertase.firebase.storage;

import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.OnPausedListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda3 implements OnPausedListener {
    public final /* synthetic */ ReactNativeFirebaseStorageDownloadTask f$0;

    public /* synthetic */ ReactNativeFirebaseStorageDownloadTask$$ExternalSyntheticLambda3(ReactNativeFirebaseStorageDownloadTask reactNativeFirebaseStorageDownloadTask) {
        this.f$0 = reactNativeFirebaseStorageDownloadTask;
    }

    public final void onPaused(Object obj) {
        this.f$0.lambda$addEventListeners$3((FileDownloadTask.TaskSnapshot) obj);
    }
}
