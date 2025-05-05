package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzlz implements zzln {
    private final zzzc zza;
    private final zzzc zzb;

    static zzlz zza(byte[] bArr, byte[] bArr2, zzya zzya) throws GeneralSecurityException {
        zzyb.zza(zzyb.zza(zzya, zzyd.UNCOMPRESSED, bArr2), zzyb.zza(zzya, bArr));
        return new zzlz(bArr, bArr2);
    }

    public final zzzc zza() {
        return this.zza;
    }

    public final zzzc zzb() {
        return this.zzb;
    }

    private zzlz(byte[] bArr, byte[] bArr2) {
        this.zza = zzzc.zza(bArr);
        this.zzb = zzzc.zza(bArr2);
    }
}
