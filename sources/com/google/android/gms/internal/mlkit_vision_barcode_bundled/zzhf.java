package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import sun.misc.Unsafe;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzhf extends zzhh {
    zzhf(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    public final void zzc(Object obj, long j, boolean z) {
        if (zzhi.zzb) {
            zzhi.zzD(obj, j, r3 ? (byte) 1 : 0);
        } else {
            zzhi.zzE(obj, j, r3 ? (byte) 1 : 0);
        }
    }

    public final void zzd(Object obj, long j, byte b) {
        if (zzhi.zzb) {
            zzhi.zzD(obj, j, b);
        } else {
            zzhi.zzE(obj, j, b);
        }
    }

    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    public final boolean zzg(Object obj, long j) {
        if (zzhi.zzb) {
            return zzhi.zzt(obj, j);
        }
        return zzhi.zzu(obj, j);
    }
}
