package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzax  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzax<K> extends zzau<K> {
    private final transient zzap<K, ?> zza;
    private final transient zzal<K> zzb;

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    /* access modifiers changed from: package-private */
    public final boolean zze() {
        return true;
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzal<K> zzc() {
        return this.zzb;
    }

    public final zzbc<K> zzd() {
        return (zzbc) zzc().iterator();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    zzax(zzap<K, ?> zzap, zzal<K> zzal) {
        this.zza = zzap;
        this.zzb = zzal;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }
}
