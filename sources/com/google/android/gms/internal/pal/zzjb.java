package com.google.android.gms.internal.pal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzjb {
    Object[] zza = new Object[8];
    int zzb = 0;
    zzja zzc;

    private final void zzd(int i) {
        int i2 = i + i;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (i2 > length) {
            int i3 = length + (length >> 1) + 1;
            if (i3 < i2) {
                int highestOneBit = Integer.highestOneBit(i2 - 1);
                i3 = highestOneBit + highestOneBit;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i3);
        }
    }

    public final zzjb zza(Object obj, Object obj2) {
        zzd(this.zzb + 1);
        zziu.zza(obj, obj2);
        Object[] objArr = this.zza;
        int i = this.zzb;
        int i2 = i + i;
        objArr[i2] = obj;
        objArr[i2 + 1] = obj2;
        this.zzb = i + 1;
        return this;
    }

    public final zzjb zzb(Map map) {
        Set<Map.Entry> entrySet = map.entrySet();
        if (entrySet instanceof Collection) {
            zzd(this.zzb + entrySet.size());
        }
        for (Map.Entry entry : entrySet) {
            zza(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public final zzjc zzc() {
        zzja zzja = this.zzc;
        if (zzja == null) {
            zzjj zzk = zzjj.zzk(this.zzb, this.zza, this);
            zzja zzja2 = this.zzc;
            if (zzja2 == null) {
                return zzk;
            }
            throw zzja2.zza();
        }
        throw zzja.zza();
    }
}
