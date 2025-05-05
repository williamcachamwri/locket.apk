package com.google.ads.interactivemedia.v3.internal;

import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzxx implements Map.Entry {
    zzxx zza;
    zzxx zzb;
    zzxx zzc;
    zzxx zzd;
    zzxx zze;
    final Object zzf;
    final boolean zzg;
    Object zzh;
    int zzi;

    zzxx(boolean z) {
        this.zzf = null;
        this.zzg = z;
        this.zze = this;
        this.zzd = this;
    }

    zzxx(boolean z, zzxx zzxx, Object obj, zzxx zzxx2, zzxx zzxx3) {
        this.zza = zzxx;
        this.zzf = obj;
        this.zzg = z;
        this.zzi = 1;
        this.zzd = zzxx2;
        this.zze = zzxx3;
        zzxx3.zzd = this;
        zzxx2.zze = this;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = this.zzf;
            if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
                Object obj3 = this.zzh;
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
        return this.zzh;
    }

    public final int hashCode() {
        Object obj = this.zzf;
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.zzh;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }

    public final Object setValue(Object obj) {
        if (obj != null || this.zzg) {
            Object obj2 = this.zzh;
            this.zzh = obj;
            return obj2;
        }
        throw new NullPointerException("value == null");
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzf);
        String valueOf2 = String.valueOf(this.zzh);
        return valueOf + "=" + valueOf2;
    }
}
