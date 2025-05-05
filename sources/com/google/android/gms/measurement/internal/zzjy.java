package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzjy implements SharedPreferences.OnSharedPreferenceChangeListener {
    private /* synthetic */ zzjq zza;

    public /* synthetic */ zzjy(zzjq zzjq) {
        this.zza = zzjq;
    }

    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.zza.zza(sharedPreferences, str);
    }
}
