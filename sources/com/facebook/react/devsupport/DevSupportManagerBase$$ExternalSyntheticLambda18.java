package com.facebook.react.devsupport;

import com.facebook.react.devsupport.DevInternalSettings;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda18 implements DevInternalSettings.Listener {
    public final /* synthetic */ DevSupportManagerBase f$0;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda18(DevSupportManagerBase devSupportManagerBase) {
        this.f$0 = devSupportManagerBase;
    }

    public final void onInternalSettingsChanged() {
        this.f$0.reloadSettings();
    }
}
