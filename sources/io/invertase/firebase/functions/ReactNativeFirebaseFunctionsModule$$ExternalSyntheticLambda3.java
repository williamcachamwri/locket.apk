package io.invertase.firebase.functions;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda3 implements OnFailureListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda3(Promise promise) {
        this.f$0 = promise;
    }

    public final void onFailure(Exception exc) {
        ReactNativeFirebaseFunctionsModule.lambda$httpsCallable$1(this.f$0, exc);
    }
}
