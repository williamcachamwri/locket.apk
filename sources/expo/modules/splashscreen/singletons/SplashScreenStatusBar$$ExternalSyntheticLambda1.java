package expo.modules.splashscreen.singletons;

import android.app.Activity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SplashScreenStatusBar$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ SplashScreenStatusBar$$ExternalSyntheticLambda1(Activity activity, boolean z) {
        this.f$0 = activity;
        this.f$1 = z;
    }

    public final void run() {
        SplashScreenStatusBar.configureTranslucent$lambda$2$lambda$1(this.f$0, this.f$1);
    }
}
