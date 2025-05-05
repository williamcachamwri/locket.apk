package com.google.ads.interactivemedia.v3.internal;

import android.app.UiModeManager;
import android.content.Context;
import com.google.ads.interactivemedia.omid.library.adsession.zzg;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzdk {
    private static UiModeManager zza;

    public static zzg zza() {
        UiModeManager uiModeManager = zza;
        if (uiModeManager == null) {
            return zzg.OTHER;
        }
        int currentModeType = uiModeManager.getCurrentModeType();
        if (currentModeType == 1) {
            return zzg.MOBILE;
        }
        if (currentModeType != 4) {
            return zzg.OTHER;
        }
        return zzg.CTV;
    }

    public static void zzb(Context context) {
        if (context != null) {
            zza = (UiModeManager) context.getSystemService("uimode");
        }
    }
}
