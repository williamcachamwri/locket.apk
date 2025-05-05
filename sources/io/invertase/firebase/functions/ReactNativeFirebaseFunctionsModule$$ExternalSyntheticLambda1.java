package io.invertase.firebase.functions;

import com.facebook.react.bridge.Promise;
import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda1 implements OnFailureListener {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ ReactNativeFirebaseFunctionsModule$$ExternalSyntheticLambda1(Promise promise) {
        this.f$0 = promise;
    }

    public final void onFailure(Exception exc) {
        ReactNativeFirebaseFunctionsModule.lambda$httpsCallableFromUrl$3(this.f$0, exc);
    }
}
