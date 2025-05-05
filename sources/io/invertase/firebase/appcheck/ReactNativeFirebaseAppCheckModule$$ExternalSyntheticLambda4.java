package io.invertase.firebase.appcheck;

import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.FirebaseAppCheck;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda4 implements FirebaseAppCheck.AppCheckListener {
    public final /* synthetic */ String f$0;

    public /* synthetic */ ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda4(String str) {
        this.f$0 = str;
    }

    public final void onAppCheckTokenChanged(AppCheckToken appCheckToken) {
        ReactNativeFirebaseAppCheckModule.lambda$addAppCheckListener$4(this.f$0, appCheckToken);
    }
}
