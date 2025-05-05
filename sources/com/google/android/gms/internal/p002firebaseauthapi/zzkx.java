package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzkx implements zzkw {
    private final zziv zza;
    private final int zzb;

    public final int zza() {
        return this.zzb;
    }

    public zzkx(zziv zziv) {
        this.zza = zziv;
        this.zzb = zziv.zzb();
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        if (bArr2.length >= i) {
            return zzxt.zza(zzio.zzb().zza(this.zza).zza(zzze.zza(bArr, zzbq.zza())).zza()).zza(Arrays.copyOfRange(bArr2, i, bArr2.length), zzks.zza);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
