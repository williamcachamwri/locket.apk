package com.google.android.gms.internal.pal;

import java.math.BigInteger;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzzd extends zzyy {
    private final Object zza;

    public zzzd(Boolean bool) {
        this.zza = bool;
    }

    public zzzd(Number number) {
        this.zza = number;
    }

    public zzzd(String str) {
        str.getClass();
        this.zza = str;
    }

    private static boolean zzh(zzzd zzzd) {
        Object obj = zzzd.zza;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzzd zzzd = (zzzd) obj;
        if (zzh(this) && zzh(zzzd)) {
            return zzb().longValue() == zzzd.zzb().longValue();
        }
        Object obj2 = this.zza;
        if (!(obj2 instanceof Number) || !(zzzd.zza instanceof Number)) {
            return obj2.equals(zzzd.zza);
        }
        double doubleValue = zzb().doubleValue();
        double doubleValue2 = zzzd.zzb().doubleValue();
        if (doubleValue == doubleValue2) {
            return true;
        }
        if (Double.isNaN(doubleValue)) {
            return Double.isNaN(doubleValue2);
        }
        return false;
    }

    public final int hashCode() {
        long doubleToLongBits;
        if (zzh(this)) {
            doubleToLongBits = zzb().longValue();
        } else {
            Object obj = this.zza;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            doubleToLongBits = Double.doubleToLongBits(zzb().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public final int zza() {
        return this.zza instanceof Number ? zzb().intValue() : Integer.parseInt(zzd());
    }

    public final Number zzb() {
        Object obj = this.zza;
        return obj instanceof String ? new zzzj((String) obj) : (Number) obj;
    }

    public final boolean zzc() {
        Object obj = this.zza;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return Boolean.parseBoolean(zzd());
    }

    public final String zzd() {
        Object obj = this.zza;
        if (obj instanceof Number) {
            return zzb().toString();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).toString();
        }
        return (String) obj;
    }

    public final boolean zze() {
        return this.zza instanceof Boolean;
    }

    public final boolean zzg() {
        return this.zza instanceof Number;
    }
}
