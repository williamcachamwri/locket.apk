package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzas  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzas<K, V> {
    zzar zza;
    private Object[] zzb;
    private int zzc;

    public final zzas<K, V> zza(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        if (iterable instanceof Collection) {
            zza(this.zzc + ((Collection) iterable).size());
        }
        for (Map.Entry entry : iterable) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            zza(this.zzc + 1);
            zzai.zza(key, value);
            Object[] objArr = this.zzb;
            int i = this.zzc;
            objArr[i * 2] = key;
            objArr[(i * 2) + 1] = value;
            this.zzc = i + 1;
        }
        return this;
    }

    public final zzap<K, V> zza() {
        zzar zzar = this.zza;
        if (zzar == null) {
            zzaw zza2 = zzaw.zza(this.zzc, this.zzb, this);
            zzar zzar2 = this.zza;
            if (zzar2 == null) {
                return zza2;
            }
            throw zzar2.zza();
        }
        throw zzar.zza();
    }

    public zzas() {
        this(4);
    }

    zzas(int i) {
        this.zzb = new Object[(i * 2)];
        this.zzc = 0;
    }

    private final void zza(int i) {
        int i2 = i << 1;
        Object[] objArr = this.zzb;
        if (i2 > objArr.length) {
            this.zzb = Arrays.copyOf(objArr, zzam.zza(objArr.length, i2));
        }
    }
}
