package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzro {
    Object[] zza;
    int zzb;
    zzrn zzc;

    public zzro() {
        this(4);
    }

    private final void zzd(int i) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        int i2 = i + i;
        if (i2 > length) {
            this.zza = Arrays.copyOf(objArr, zzrh.zza(length, i2));
        }
    }

    public final zzro zza(Object obj, Object obj2) {
        zzd(this.zzb + 1);
        zzqt.zzb(obj, obj2);
        Object[] objArr = this.zza;
        int i = this.zzb;
        int i2 = i + i;
        objArr[i2] = obj;
        objArr[i2 + 1] = obj2;
        this.zzb = i + 1;
        return this;
    }

    public final zzro zzb(Iterable iterable) {
        if (iterable instanceof Collection) {
            zzd(this.zzb + ((Collection) iterable).size());
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            zza(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public final zzrp zzc() {
        zzrn zzrn = this.zzc;
        if (zzrn == null) {
            zzsj zzl = zzsj.zzl(this.zzb, this.zza, this);
            zzrn zzrn2 = this.zzc;
            if (zzrn2 == null) {
                return zzl;
            }
            throw zzrn2.zza();
        }
        throw zzrn.zza();
    }

    zzro(int i) {
        this.zza = new Object[(i + i)];
        this.zzb = 0;
    }
}
