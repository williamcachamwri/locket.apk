package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzlx implements zzlk {
    private final zzya zza;
    private final zzle zzb;

    static zzlx zza(zzya zzya) throws GeneralSecurityException {
        int i = zzlw.zza[zzya.ordinal()];
        if (i == 1) {
            return new zzlx(new zzle("HmacSha256"), zzya.NIST_P256);
        }
        if (i == 2) {
            return new zzlx(new zzle("HmacSha384"), zzya.NIST_P384);
        }
        if (i == 3) {
            return new zzlx(new zzle("HmacSha512"), zzya.NIST_P521);
        }
        throw new GeneralSecurityException("invalid curve type: " + String.valueOf(zzya));
    }

    private zzlx(zzle zzle, zzya zzya) {
        this.zzb = zzle;
        this.zza = zzya;
    }

    public final byte[] zza(byte[] bArr, zzln zzln) throws GeneralSecurityException {
        byte[] zza2 = zzyb.zza(zzyb.zza(this.zza, zzln.zza().zzb()), zzyb.zza(this.zza, zzyd.UNCOMPRESSED, bArr));
        byte[] zza3 = zzxv.zza(bArr, zzln.zzb().zzb());
        byte[] zza4 = zzlq.zza(zza());
        zzle zzle = this.zzb;
        return zzle.zza((byte[]) null, zza2, "eae_prk", zza3, "shared_secret", zza4, zzle.zza());
    }

    public final byte[] zza() throws GeneralSecurityException {
        int i = zzlw.zza[this.zza.ordinal()];
        if (i == 1) {
            return zzlq.zzc;
        }
        if (i == 2) {
            return zzlq.zzd;
        }
        if (i == 3) {
            return zzlq.zze;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
