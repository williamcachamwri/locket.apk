package com.locket.Locket.Ads;

import android.widget.FrameLayout;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NimbusAdsViewManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FrameLayout f$0;
    public final /* synthetic */ AdState f$1;
    public final /* synthetic */ NimbusAdLoader f$2;

    public /* synthetic */ NimbusAdsViewManager$$ExternalSyntheticLambda1(FrameLayout frameLayout, AdState adState, NimbusAdLoader nimbusAdLoader) {
        this.f$0 = frameLayout;
        this.f$1 = adState;
        this.f$2 = nimbusAdLoader;
    }

    public final void run() {
        NimbusAdsViewManager.lambda$setLoadAd$0(this.f$0, this.f$1, this.f$2);
    }
}
