package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzjp;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzxw implements zzbn {
    static final zzmm<zzya, zzjp.zzc> zza = zzmm.zza().zza(zzya.NIST_P256, zzjp.zzc.zza).zza(zzya.NIST_P384, zzjp.zzc.zzb).zza(zzya.NIST_P521, zzjp.zzc.zzc).zza();
    static final zzmm<zzyd, zzjp.zze> zzb = zzmm.zza().zza(zzyd.UNCOMPRESSED, zzjp.zze.zzb).zza(zzyd.COMPRESSED, zzjp.zze.zza).zza(zzyd.DO_NOT_USE_CRUNCHY_UNCOMPRESSED, zzjp.zze.zzc).zza();

    public static zzbn zza(zzjv zzjv) throws GeneralSecurityException {
        byte[] byteArray = zzjv.zze().getAffineX().toByteArray();
        byte[] byteArray2 = zzjv.zze().getAffineY().toByteArray();
        ECParameterSpec zza2 = zzyb.zza(zza.zza(zzjv.zzb().zzd()));
        ECPoint eCPoint = new ECPoint(new BigInteger(1, byteArray), new BigInteger(1, byteArray2));
        zzmk.zza(eCPoint, zza2.getCurve());
        ECPublicKey eCPublicKey = (ECPublicKey) zzyf.zze.zza("EC").generatePublic(new ECPublicKeySpec(eCPoint, zza2));
        byte[] bArr = new byte[0];
        if (zzjv.zzb().zzh() != null) {
            bArr = zzjv.zzb().zzh().zzb();
        }
        return new zzxw(eCPublicKey, bArr, zza(zzjv.zzb().zze()), zzb.zza(zzjv.zzb().zzf()), zzks.zza(zzjv.zzb()), zzjv.zzc().zzb());
    }

    static final String zza(zzjp.zzb zzb2) throws GeneralSecurityException {
        if (zzb2.equals(zzjp.zzb.zza)) {
            return "HmacSha1";
        }
        if (zzb2.equals(zzjp.zzb.zzb)) {
            return "HmacSha224";
        }
        if (zzb2.equals(zzjp.zzb.zzc)) {
            return "HmacSha256";
        }
        if (zzb2.equals(zzjp.zzb.zzd)) {
            return "HmacSha384";
        }
        if (zzb2.equals(zzjp.zzb.zze)) {
            return "HmacSha512";
        }
        throw new GeneralSecurityException("hash unsupported for EciesAeadHkdf: " + String.valueOf(zzb2));
    }

    private zzxw(ECPublicKey eCPublicKey, byte[] bArr, String str, zzyd zzyd, zzkw zzkw, byte[] bArr2) throws GeneralSecurityException {
        zzmk.zza(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        new zzxy(eCPublicKey);
    }
}
