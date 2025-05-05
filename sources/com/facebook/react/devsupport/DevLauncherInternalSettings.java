package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.devsupport.DevInternalSettings;
import expo.modules.devlauncher.react.DevLauncherPackagerConnectionSettings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\bH\u0014J\u0006\u0010\n\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/devsupport/DevLauncherInternalSettings;", "Lcom/facebook/react/devsupport/DevInternalSettings;", "context", "Landroid/content/Context;", "debugServerHost", "", "(Landroid/content/Context;Ljava/lang/String;)V", "packagerConnectionSettings", "Lexpo/modules/devlauncher/react/DevLauncherPackagerConnectionSettings;", "getPackagerConnectionSettings", "public_getPackagerConnectionSettings", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherInternalSettings.kt */
public final class DevLauncherInternalSettings extends DevInternalSettings {
    private DevLauncherPackagerConnectionSettings packagerConnectionSettings;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevLauncherInternalSettings(Context context, String str) {
        super(context, (DevInternalSettings.Listener) null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "debugServerHost");
        this.packagerConnectionSettings = new DevLauncherPackagerConnectionSettings(context, str);
    }

    /* access modifiers changed from: protected */
    public DevLauncherPackagerConnectionSettings getPackagerConnectionSettings() {
        return this.packagerConnectionSettings;
    }

    public final DevLauncherPackagerConnectionSettings public_getPackagerConnectionSettings() {
        return getPackagerConnectionSettings();
    }
}
