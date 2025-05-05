package com.google.android.gms.internal.atv_ads_framework;

import sun.misc.Unsafe;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
abstract class zzfy {
    final Unsafe zza;

    zzfy(Unsafe unsafe) {
        this.zza = unsafe;
    }

    public abstract double zza(Object obj, long j);

    public abstract float zzb(Object obj, long j);

    public abstract void zzc(Object obj, long j, boolean z);

    public abstract void zzd(Object obj, long j, byte b);

    public abstract void zze(Object obj, long j, double d);

    public abstract void zzf(Object obj, long j, float f);

    public abstract boolean zzg(Object obj, long j);
}
