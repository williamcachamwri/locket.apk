package com.google.ads.interactivemedia.v3.internal;

import android.content.SharedPreferences;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzmg implements zzmq {
    final /* synthetic */ SharedPreferences zza;

    zzmg(zzmh zzmh, SharedPreferences sharedPreferences) {
        this.zza = sharedPreferences;
    }

    public final Boolean zza(String str, boolean z) {
        try {
            return Boolean.valueOf(this.zza.getBoolean(str, z));
        } catch (ClassCastException unused) {
            return Boolean.valueOf(this.zza.getString(str, String.valueOf(z)));
        }
    }

    public final Long zzb(String str, long j) {
        try {
            return Long.valueOf(this.zza.getLong(str, j));
        } catch (ClassCastException unused) {
            return Long.valueOf((long) this.zza.getInt(str, (int) j));
        }
    }
}
