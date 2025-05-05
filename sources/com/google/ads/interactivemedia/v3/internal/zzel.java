package com.google.ads.interactivemedia.v3.internal;

import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.os.Build;
import android.os.ext.SdkExtensions;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import javax.annotation.Nullable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzel {
    public static int zza() {
        if (Build.VERSION.SDK_INT < 30 || SdkExtensions.getExtensionVersion(30) <= 3) {
            return 0;
        }
        return SdkExtensions.getExtensionVersion(1000000);
    }

    @Nullable
    public static Application zzb(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            return (Application) applicationContext;
        }
        return null;
    }

    public static boolean zzc(Context context, TestingConfiguration testingConfiguration) {
        if (testingConfiguration != null && testingConfiguration.forceAndroidTvMode()) {
            return true;
        }
        boolean hasSystemFeature = context.getPackageManager().hasSystemFeature("android.software.leanback");
        boolean hasSystemFeature2 = context.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
        if (!zzd(context, testingConfiguration) || !hasSystemFeature || hasSystemFeature2) {
            return false;
        }
        return true;
    }

    public static boolean zzd(Context context, TestingConfiguration testingConfiguration) {
        if (testingConfiguration != null && testingConfiguration.forceTvMode()) {
            return true;
        }
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager == null || uiModeManager.getCurrentModeType() != 4) {
            return false;
        }
        return true;
    }
}
