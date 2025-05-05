package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzajy;
import com.google.android.gms.internal.p002firebaseauthapi.zzwa;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcc {
    private final zzwa.zza zza;

    public final synchronized zzbt zza() throws GeneralSecurityException {
        return zzbt.zza((zzwa) ((zzajy) this.zza.zze()));
    }

    public static zzcc zza(zzbt zzbt) {
        zzajy.zza zzn = zzbt.zzb().zzn();
        zzajy.zza zza2 = zzn;
        return new zzcc((zzwa.zza) zzn);
    }

    private zzcc(zzwa.zza zza2) {
        this.zza = zza2;
    }
}
