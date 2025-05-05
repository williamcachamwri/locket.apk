package io.invertase.firebase.firestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseFirestoreTransactionHandler f$0;
    public final /* synthetic */ ReactNativeFirebaseEventEmitter f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ReactNativeFirebaseFirestoreTransactionModule$$ExternalSyntheticLambda1(ReactNativeFirebaseFirestoreTransactionHandler reactNativeFirebaseFirestoreTransactionHandler, ReactNativeFirebaseEventEmitter reactNativeFirebaseEventEmitter, String str) {
        this.f$0 = reactNativeFirebaseFirestoreTransactionHandler;
        this.f$1 = reactNativeFirebaseEventEmitter;
        this.f$2 = str;
    }

    public final void onComplete(Task task) {
        ReactNativeFirebaseFirestoreTransactionModule.lambda$transactionBegin$4(this.f$0, this.f$1, this.f$2, task);
    }
}
