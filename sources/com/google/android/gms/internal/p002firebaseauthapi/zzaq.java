package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaq extends zzal<E> {
    private final transient int zza;
    private final transient int zzb;
    private final /* synthetic */ zzal zzc;

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzc.zzb() + this.zza + this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final boolean zze() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzb() {
        return this.zzc.zzb() + this.zza;
    }

    public final int size() {
        return this.zzb;
    }

    public final zzal<E> zza(int i, int i2) {
        zzy.zza(i, i2, this.zzb);
        zzal zzal = this.zzc;
        int i3 = this.zza;
        return (zzal) zzal.subList(i + i3, i2 + i3);
    }

    public final E get(int i) {
        zzy.zza(i, this.zzb);
        return this.zzc.get(i + this.zza);
    }

    public final /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    zzaq(zzal zzal, int i, int i2) {
        this.zzc = zzal;
        this.zza = i;
        this.zzb = i2;
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public final Object[] zzf() {
        return this.zzc.zzf();
    }
}
