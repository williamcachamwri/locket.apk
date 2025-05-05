package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzat  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzat<E> extends zzal<E> {
    static final zzal<Object> zza = new zzat(new Object[0], 0);
    private final transient Object[] zzb;
    private final transient int zzc;

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, i, this.zzc);
        return i + this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zze() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzc;
    }

    public final int size() {
        return this.zzc;
    }

    public final E get(int i) {
        zzy.zza(i, this.zzc);
        return Objects.requireNonNull(this.zzb[i]);
    }

    zzat(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzf() {
        return this.zzb;
    }
}
