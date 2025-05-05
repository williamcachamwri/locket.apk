package com.google.android.gms.internal.pal;

import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzafq extends zzafr {
    zzafq(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    public final void zzc(Object obj, long j, boolean z) {
        if (zzafs.zzb) {
            zzafs.zzD(obj, j, r3 ? (byte) 1 : 0);
        } else {
            zzafs.zzE(obj, j, r3 ? (byte) 1 : 0);
        }
    }

    public final void zzd(Object obj, long j, byte b) {
        if (zzafs.zzb) {
            zzafs.zzD(obj, j, b);
        } else {
            zzafs.zzE(obj, j, b);
        }
    }

    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    public final boolean zzg(Object obj, long j) {
        if (zzafs.zzb) {
            return zzafs.zzt(obj, j);
        }
        return zzafs.zzu(obj, j);
    }
}
