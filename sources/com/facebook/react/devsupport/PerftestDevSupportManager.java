package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.devsupport.DevInternalSettings;
import com.facebook.react.devsupport.InspectorPackagerConnection;

public final class PerftestDevSupportManager extends DisabledDevSupportManager {
    private final InspectorPackagerConnection.BundleStatus mBundleStatus = new InspectorPackagerConnection.BundleStatus();
    private final DevServerHelper mDevServerHelper;
    private final DevInternalSettings mDevSettings;

    public PerftestDevSupportManager(Context context) {
        DevInternalSettings devInternalSettings = new DevInternalSettings(context, new DevInternalSettings.Listener() {
            public void onInternalSettingsChanged() {
            }
        });
        this.mDevSettings = devInternalSettings;
        this.mDevServerHelper = new DevServerHelper(devInternalSettings, context.getPackageName(), new PerftestDevSupportManager$$ExternalSyntheticLambda0(this), devInternalSettings.getPackagerConnectionSettings());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ InspectorPackagerConnection.BundleStatus lambda$new$0() {
        return this.mBundleStatus;
    }

    public DevInternalSettings getDevSettings() {
        return this.mDevSettings;
    }

    public void startInspector() {
        this.mDevServerHelper.openInspectorConnection();
    }

    public void stopInspector() {
        this.mDevServerHelper.closeInspectorConnection();
    }
}
