package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzhv implements SharedPreferences.OnSharedPreferenceChangeListener {
    private /* synthetic */ zzhw zza;

    public /* synthetic */ zzhv(zzhw zzhw) {
        this.zza = zzhw;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
