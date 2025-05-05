package expo.modules.adapters.react.apploader;

import com.facebook.react.ReactInstanceManager;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RNHeadlessAppLoader$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ReactInstanceManager f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ RNHeadlessAppLoader$$ExternalSyntheticLambda1(ReactInstanceManager reactInstanceManager, String str) {
        this.f$0 = reactInstanceManager;
        this.f$1 = str;
    }

    public final void run() {
        RNHeadlessAppLoader.invalidateApp$lambda$1(this.f$0, this.f$1);
    }
}
