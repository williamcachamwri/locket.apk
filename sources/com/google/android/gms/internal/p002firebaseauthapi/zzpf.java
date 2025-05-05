package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpf implements zzpj {
    private final zzzc zza;
    private final zzvu zzb;

    public static zzpf zza(zzvu zzvu) throws GeneralSecurityException {
        return new zzpf(zzvu, zzpr.zza(zzvu.zzf()));
    }

    public static zzpf zzb(zzvu zzvu) {
        return new zzpf(zzvu, zzpr.zzb(zzvu.zzf()));
    }

    public final zzvu zza() {
        return this.zzb;
    }

    public final zzzc zzc() {
        return this.zza;
    }

    private zzpf(zzvu zzvu, zzzc zzzc) {
        this.zzb = zzvu;
        this.zza = zzzc;
    }
}
