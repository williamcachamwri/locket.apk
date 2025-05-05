package com.google.android.gms.internal.atv_ads_framework;

import android.net.Uri;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzd {
    public static final Uri zza;
    public static final Uri zzb;
    public static final zzbi zzc = zzbi.zzk(zzc.ATC_RENDERING_CAPABILITY.zza(), zzc.ATC_RENDERING_CAPABILITY_DEPRECATED.zza());
    private static final Uri zzd;

    static {
        Uri build = new Uri.Builder().scheme("content").appendPath("signals").build();
        zzd = build;
        zza = build.buildUpon().authority("com.google.android.apps.tv.launcherx.ads.signals.AdsSignalsContentProvider").build();
        zzb = build.buildUpon().authority("com.google.android.tvrecommendations.ads.signals.AdsSignalsContentProvider").build();
    }
}
