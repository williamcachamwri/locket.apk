package androidx.browser.customtabs;

import android.os.Bundle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CustomTabsSession$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ EngagementSignalsCallback f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ CustomTabsSession$1$$ExternalSyntheticLambda1(EngagementSignalsCallback engagementSignalsCallback, boolean z, Bundle bundle) {
        this.f$0 = engagementSignalsCallback;
        this.f$1 = z;
        this.f$2 = bundle;
    }

    public final void run() {
        this.f$0.onSessionEnded(this.f$1, this.f$2);
    }
}
