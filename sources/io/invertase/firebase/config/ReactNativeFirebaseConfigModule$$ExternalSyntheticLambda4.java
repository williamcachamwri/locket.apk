package io.invertase.firebase.config;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda4 implements OnCompleteListener {
    public final /* synthetic */ ReactNativeFirebaseConfigModule f$0;
    public final /* synthetic */ Promise f$1;

    public /* synthetic */ ReactNativeFirebaseConfigModule$$ExternalSyntheticLambda4(ReactNativeFirebaseConfigModule reactNativeFirebaseConfigModule, Promise promise) {
        this.f$0 = reactNativeFirebaseConfigModule;
        this.f$1 = promise;
    }

    public final void onComplete(Task task) {
        this.f$0.lambda$fetch$1(this.f$1, task);
    }
}
