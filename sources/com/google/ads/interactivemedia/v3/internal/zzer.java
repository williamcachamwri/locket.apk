package com.google.ads.interactivemedia.v3.internal;

import android.content.SharedPreferences;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzer implements SharedPreferences.OnSharedPreferenceChangeListener {
    final /* synthetic */ zzet zza;

    zzer(zzet zzet) {
        this.zza = zzet;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        zzet zzet = this.zza;
        zzet.zzd = zzet.zzf();
    }
}
