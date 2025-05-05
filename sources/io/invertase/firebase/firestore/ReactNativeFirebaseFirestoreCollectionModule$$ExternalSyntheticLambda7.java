package io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda7 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseFirestoreCollectionModule f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ReadableArray f$4;
    public final /* synthetic */ ReadableArray f$5;
    public final /* synthetic */ ReadableMap f$6;
    public final /* synthetic */ ReadableMap f$7;

    public /* synthetic */ ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda7(ReactNativeFirebaseFirestoreCollectionModule reactNativeFirebaseFirestoreCollectionModule, String str, String str2, int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2) {
        this.f$0 = reactNativeFirebaseFirestoreCollectionModule;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = i;
        this.f$4 = readableArray;
        this.f$5 = readableArray2;
        this.f$6 = readableMap;
        this.f$7 = readableMap2;
    }

    public final void onComplete(Task task) {
        this.f$0.lambda$namedQueryOnSnapshot$0(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, task);
    }
}
