package com.google.android.gms.internal.auth;

import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
abstract class zzhi {
    final Unsafe zza;

    zzhi(Unsafe unsafe) {
        this.zza = unsafe;
    }

    public abstract double zza(Object obj, long j);

    public abstract float zzb(Object obj, long j);

    public abstract void zzc(Object obj, long j, boolean z);

    public abstract void zzd(Object obj, long j, double d);

    public abstract void zze(Object obj, long j, float f);

    public abstract boolean zzf(Object obj, long j);
}
