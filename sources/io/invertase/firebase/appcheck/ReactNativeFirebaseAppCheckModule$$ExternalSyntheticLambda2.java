package io.invertase.firebase.appcheck;

import com.google.firebase.FirebaseApp;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ FirebaseApp f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda2(FirebaseApp firebaseApp, boolean z) {
        this.f$0 = firebaseApp;
        this.f$1 = z;
    }

    public final Object call() {
        return ReactNativeFirebaseAppCheckModule.lambda$getToken$0(this.f$0, this.f$1);
    }
}
