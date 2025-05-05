package io.invertase.firebase.storage;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseStorageModule f$0;
    public final /* synthetic */ ReadableMap f$1;
    public final /* synthetic */ StorageReference f$2;
    public final /* synthetic */ Promise f$3;

    public /* synthetic */ ReactNativeFirebaseStorageModule$$ExternalSyntheticLambda0(ReactNativeFirebaseStorageModule reactNativeFirebaseStorageModule, ReadableMap readableMap, StorageReference storageReference, Promise promise) {
        this.f$0 = reactNativeFirebaseStorageModule;
        this.f$1 = readableMap;
        this.f$2 = storageReference;
        this.f$3 = promise;
    }

    public final void onComplete(Task task) {
        this.f$0.lambda$updateMetadata$6(this.f$1, this.f$2, this.f$3, task);
    }
}
