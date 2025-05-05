package com.google.android.gms.internal.pal;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzzq implements Map.Entry {
    zzzq zza;
    zzzq zzb;
    zzzq zzc;
    zzzq zzd;
    zzzq zze;
    final Object zzf;
    Object zzg;
    int zzh;

    zzzq() {
        this.zzf = null;
        this.zze = this;
        this.zzd = this;
    }

    zzzq(zzzq zzzq, Object obj, zzzq zzzq2, zzzq zzzq3) {
        this.zza = zzzq;
        this.zzf = obj;
        this.zzh = 1;
        this.zzd = zzzq2;
        this.zze = zzzq3;
        zzzq3.zzd = this;
        zzzq2.zze = this;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.zzf;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.zzg;
                if (obj3 == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (!obj3.equals(entry.getValue())) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public final Object getKey() {
        return this.zzf;
    }

    public final Object getValue() {
        return this.zzg;
    }

    public final Object setValue(Object obj) {
        Object obj2 = this.zzg;
        this.zzg = obj;
        return obj2;
    }

    public final String toString() {
        return this.zzf + "=" + this.zzg;
    }

    public final int hashCode() {
        int i;
        Object obj = this.zzf;
        int i2 = 0;
        if (obj == null) {
            i = 0;
        } else {
            i = obj.hashCode();
        }
        Object obj2 = this.zzg;
        if (obj2 != null) {
            i2 = obj2.hashCode();
        }
        return i ^ i2;
    }
}
