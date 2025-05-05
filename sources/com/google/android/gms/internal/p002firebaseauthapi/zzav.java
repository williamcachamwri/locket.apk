package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzav  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzav<K, V> extends zzau<Map.Entry<K, V>> {
    private final transient zzap<K, V> zza;
    /* access modifiers changed from: private */
    public final transient Object[] zzb;
    /* access modifiers changed from: private */
    public final transient int zzc;

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    /* access modifiers changed from: package-private */
    public final boolean zze() {
        return true;
    }

    public final int size() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzal<Map.Entry<K, V>> zzg() {
        return new zzay(this);
    }

    public final zzbc<Map.Entry<K, V>> zzd() {
        return (zzbc) zzc().iterator();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    zzav(zzap<K, V> zzap, Object[] objArr, int i, int i2) {
        this.zza = zzap;
        this.zzb = objArr;
        this.zzc = i2;
    }

    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.zza.get(key))) {
                return false;
            }
            return true;
        }
        return false;
    }
}
