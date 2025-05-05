package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzgs {
    public String zza;
    public String zzb;
    public Bundle zzc;
    private long zzd;

    public final zzbf zza() {
        return new zzbf(this.zza, new zzbe(new Bundle(this.zzc)), this.zzb, this.zzd);
    }

    public static zzgs zza(zzbf zzbf) {
        return new zzgs(zzbf.zza, zzbf.zzc, zzbf.zzb.zzb(), zzbf.zzd);
    }

    public final String toString() {
        String str = this.zzb;
        String str2 = this.zza;
        return "origin=" + str + ",name=" + str2 + ",params=" + String.valueOf(this.zzc);
    }

    public zzgs(String str, String str2, Bundle bundle, long j) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = bundle == null ? new Bundle() : bundle;
        this.zzd = j;
    }
}
