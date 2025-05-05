package com.facebook.react.devsupport;

import com.facebook.react.common.ShakeDetector;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda20 implements ShakeDetector.ShakeListener {
    public final /* synthetic */ DevSupportManagerBase f$0;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda20(DevSupportManagerBase devSupportManagerBase) {
        this.f$0 = devSupportManagerBase;
    }

    public final void onShake() {
        this.f$0.showDevOptionsDialog();
    }
}
