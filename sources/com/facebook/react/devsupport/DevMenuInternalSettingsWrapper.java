package com.facebook.react.devsupport;

import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR$\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8F@FX\u000e¢\u0006\f\u001a\u0004\b\f\u0010\n\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8F@FX\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\u000eR$\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/devsupport/DevMenuInternalSettingsWrapper;", "", "developerSettings", "Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;", "(Lcom/facebook/react/modules/debug/interfaces/DeveloperSettings;)V", "devSettings", "Lcom/facebook/react/devsupport/DevInternalSettings;", "(Lcom/facebook/react/devsupport/DevInternalSettings;)V", "isFpsDebugEnabled", "", "()Z", "value", "isHotModuleReplacementEnabled", "setHotModuleReplacementEnabled", "(Z)V", "isJSDevModeEnabled", "setJSDevModeEnabled", "isRemoteJSDebugEnabled", "setRemoteJSDebugEnabled", "packagerConnectionSettings", "Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;", "getPackagerConnectionSettings", "()Lcom/facebook/react/packagerconnection/PackagerConnectionSettings;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuReactInternalSettings.kt */
public final class DevMenuInternalSettingsWrapper {
    private final DevInternalSettings devSettings;
    private final boolean isFpsDebugEnabled;
    private final PackagerConnectionSettings packagerConnectionSettings;

    public DevMenuInternalSettingsWrapper(DevInternalSettings devInternalSettings) {
        Intrinsics.checkNotNullParameter(devInternalSettings, "devSettings");
        this.devSettings = devInternalSettings;
        this.isFpsDebugEnabled = devInternalSettings.isFpsDebugEnabled();
        PackagerConnectionSettings packagerConnectionSettings2 = devInternalSettings.getPackagerConnectionSettings();
        Intrinsics.checkNotNullExpressionValue(packagerConnectionSettings2, "getPackagerConnectionSettings(...)");
        this.packagerConnectionSettings = packagerConnectionSettings2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DevMenuInternalSettingsWrapper(DeveloperSettings developerSettings) {
        this((DevInternalSettings) developerSettings);
        Intrinsics.checkNotNullParameter(developerSettings, "developerSettings");
    }

    public final boolean isFpsDebugEnabled() {
        return this.isFpsDebugEnabled;
    }

    public final boolean isHotModuleReplacementEnabled() {
        return this.devSettings.isHotModuleReplacementEnabled();
    }

    public final void setHotModuleReplacementEnabled(boolean z) {
        this.devSettings.setHotModuleReplacementEnabled(z);
    }

    public final boolean isRemoteJSDebugEnabled() {
        return this.devSettings.isRemoteJSDebugEnabled();
    }

    public final void setRemoteJSDebugEnabled(boolean z) {
        this.devSettings.setRemoteJSDebugEnabled(z);
    }

    public final boolean isJSDevModeEnabled() {
        return this.devSettings.isJSDevModeEnabled();
    }

    public final void setJSDevModeEnabled(boolean z) {
        this.devSettings.setJSDevModeEnabled(z);
    }

    public final PackagerConnectionSettings getPackagerConnectionSettings() {
        return this.packagerConnectionSettings;
    }
}
