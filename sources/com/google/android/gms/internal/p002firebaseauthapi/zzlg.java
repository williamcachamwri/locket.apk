package com.google.android.gms.internal.p002firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzlg {
    private static final byte[] zza = new byte[0];
    private final zzlh zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private BigInteger zzf = BigInteger.ZERO;

    public static zzlg zza(byte[] bArr, zzln zzln, zzlk zzlk, zzll zzll, zzlh zzlh, byte[] bArr2) throws GeneralSecurityException {
        byte[] zza2 = zzlk.zza(bArr, zzln);
        byte[] bArr3 = zzlq.zza;
        byte[] zza3 = zzlq.zza(zzlk.zza(), zzll.zzb(), zzlh.zzc());
        byte[] bArr4 = zzlq.zzl;
        byte[] bArr5 = zza;
        byte[] zza4 = zzxv.zza(bArr3, zzll.zza(bArr4, bArr5, "psk_id_hash", zza3), zzll.zza(zzlq.zzl, bArr2, "info_hash", zza3));
        zzll zzll2 = zzll;
        byte[] zza5 = zzll.zza(zza2, bArr5, "secret", zza3);
        byte[] bArr6 = zza4;
        byte[] bArr7 = zza3;
        byte[] zza6 = zzll2.zza(zza5, bArr6, "key", bArr7, zzlh.zza());
        byte[] zza7 = zzll2.zza(zza5, bArr6, "base_nonce", bArr7, zzlh.zzb());
        zzlh.zzb();
        return new zzlg(bArr, zza6, zza7, BigInteger.ONE.shiftLeft(96).subtract(BigInteger.ONE), zzlh);
    }

    private zzlg(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzlh zzlh) {
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzlh;
    }

    private final synchronized byte[] zza() throws GeneralSecurityException {
        byte[] zza2;
        zza2 = zzxv.zza(this.zze, zzmj.zza(this.zzf, this.zzb.zzb()));
        if (this.zzf.compareTo(this.zzc) < 0) {
            this.zzf = this.zzf.add(BigInteger.ONE);
        } else {
            throw new GeneralSecurityException("message limit reached");
        }
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public final byte[] zza(byte[] bArr, int i, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zza(this.zzd, zza(), bArr, i, bArr2);
    }
}
