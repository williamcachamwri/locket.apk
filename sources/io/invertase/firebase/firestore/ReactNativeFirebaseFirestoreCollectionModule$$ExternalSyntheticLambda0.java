package io.invertase.firebase.firestore;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ ReadableArray f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda0(ReadableArray readableArray, Promise promise) {
        this.f$0 = readableArray;
        this.f$1 = promise;
    }

    public final void onComplete(Task task) {
        ReactNativeFirebaseFirestoreCollectionModule.lambda$aggregateQuery$3(this.f$0, this.f$1, task);
    }
}
