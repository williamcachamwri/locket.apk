package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzly  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzly implements zzlk {
    private final zzle zza;

    zzly(zzle zzle) {
        this.zza = zzle;
    }

    public final byte[] zza(byte[] bArr, zzln zzln) throws GeneralSecurityException {
        byte[] zza2 = zzza.zza(zzln.zza().zzb(), bArr);
        byte[] zza3 = zzxv.zza(bArr, zzln.zzb().zzb());
        byte[] zza4 = zzlq.zza(zzlq.zzb);
        zzle zzle = this.zza;
        return zzle.zza((byte[]) null, zza2, "eae_prk", zza3, "shared_secret", zza4, zzle.zza());
    }

    public final byte[] zza() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzb(), zzlq.zzf)) {
            return zzlq.zzb;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
