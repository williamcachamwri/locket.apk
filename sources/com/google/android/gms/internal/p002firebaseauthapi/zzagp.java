package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzagp {
    private final String zza;
    private final zzaia zzb;

    public final zzaia zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }

    public zzagp(String str, zzaia zzaia) {
        this.zza = Preconditions.checkNotEmpty(str);
        this.zzb = (zzaia) Preconditions.checkNotNull(zzaia);
    }
}
