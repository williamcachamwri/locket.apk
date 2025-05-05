package com.google.android.gms.internal.atv_ads_framework;

import expo.modules.devlauncher.launcher.manifest.DevLauncherNavigationBarVisibility;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public enum zza {
    TV_LAUNCHER("watson"),
    LAUNCHER_X("launcher_x"),
    CUSTOM("custom"),
    LEANBACK(DevLauncherNavigationBarVisibility.LEANBACK),
    FIRE_TV("fire_tv"),
    UNKNOWN("unknown");
    
    private final String zzh;

    private zza(String str) {
        this.zzh = str;
    }

    public final String zza() {
        return this.zzh;
    }
}
