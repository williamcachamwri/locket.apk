package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.NoSuchElementException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaio  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaio extends zzaiq {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzaip zzc;

    public final byte zza() {
        int i = this.zza;
        if (i < this.zzb) {
            this.zza = i + 1;
            return this.zzc.zzb(i);
        }
        throw new NoSuchElementException();
    }

    zzaio(zzaip zzaip) {
        this.zzc = zzaip;
        this.zzb = zzaip.zzb();
    }

    public final boolean hasNext() {
        return this.zza < this.zzb;
    }
}
