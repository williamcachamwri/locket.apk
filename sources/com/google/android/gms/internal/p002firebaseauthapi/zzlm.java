package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzju;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import okio.Utf8;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzlm {
    private static final zzor<zzjw, zzbo> zza = zzor.zza(new zzlp(), zzjw.class, zzbo.class);
    private static final zzor<zzke, zzbn> zzb = zzor.zza(new zzlo(), zzke.class, zzbn.class);
    private static final zzcj<zzbo> zzc = zzna.zza("type.googleapis.com/google.crypto.tink.HpkePrivateKey", zzbo.class, zzvk.zzf());
    private static final zzbs<zzbn> zzd = zzna.zza("type.googleapis.com/google.crypto.tink.HpkePublicKey", zzbn.class, zzvq.zzb.ASYMMETRIC_PUBLIC, zzvn.d_());
    private static final zznx<zzju> zze = new zzlr();

    public static /* synthetic */ zzjw zza(zzju zzju, Integer num) {
        zzze zzze;
        zzzc zzzc;
        byte[] bArr;
        if (zzju.zze().equals(zzju.zzf.zzd)) {
            byte[] zza2 = zzpe.zza(32);
            zza2[0] = (byte) (zza2[0] | 7);
            byte b = (byte) (zza2[31] & Utf8.REPLACEMENT_BYTE);
            zza2[31] = b;
            zza2[31] = (byte) (b | 128);
            zzze = zzze.zza(zza2, zzbq.zza());
            zzzc = zzzc.zza(zzza.zza(zza2));
        } else if (zzju.zze().equals(zzju.zzf.zza) || zzju.zze().equals(zzju.zzf.zzb) || zzju.zze().equals(zzju.zzf.zzc)) {
            zzya zzc2 = zzlq.zzc(zzju.zze());
            KeyPair zza3 = zzyb.zza(zzyb.zza(zzc2));
            zzyd zzyd = zzyd.UNCOMPRESSED;
            ECPoint w = ((ECPublicKey) zza3.getPublic()).getW();
            EllipticCurve curve = zzyb.zza(zzc2).getCurve();
            zzmk.zza(w, curve);
            int zza4 = zzyb.zza(curve);
            int ordinal = zzyd.ordinal();
            if (ordinal != 0) {
                int i = 2;
                if (ordinal == 1) {
                    int i2 = zza4 + 1;
                    bArr = new byte[i2];
                    byte[] zza5 = zzmj.zza(w.getAffineX());
                    System.arraycopy(zza5, 0, bArr, i2 - zza5.length, zza5.length);
                    if (w.getAffineY().testBit(0)) {
                        i = 3;
                    }
                    bArr[0] = (byte) i;
                } else if (ordinal == 2) {
                    int i3 = zza4 * 2;
                    bArr = new byte[i3];
                    byte[] zza6 = zzmj.zza(w.getAffineX());
                    if (zza6.length > zza4) {
                        zza6 = Arrays.copyOfRange(zza6, zza6.length - zza4, zza6.length);
                    }
                    byte[] zza7 = zzmj.zza(w.getAffineY());
                    if (zza7.length > zza4) {
                        zza7 = Arrays.copyOfRange(zza7, zza7.length - zza4, zza7.length);
                    }
                    System.arraycopy(zza7, 0, bArr, i3 - zza7.length, zza7.length);
                    System.arraycopy(zza6, 0, bArr, zza4 - zza6.length, zza6.length);
                } else {
                    throw new GeneralSecurityException("invalid format:" + String.valueOf(zzyd));
                }
            } else {
                int i4 = (zza4 * 2) + 1;
                bArr = new byte[i4];
                byte[] zza8 = zzmj.zza(w.getAffineX());
                byte[] zza9 = zzmj.zza(w.getAffineY());
                System.arraycopy(zza9, 0, bArr, i4 - zza9.length, zza9.length);
                System.arraycopy(zza8, 0, bArr, (zza4 + 1) - zza8.length, zza8.length);
                bArr[0] = 4;
            }
            zzzc = zzzc.zza(bArr);
            zzze = zzze.zza(zzmj.zza(((ECPrivateKey) zza3.getPrivate()).getS(), zzlq.zza(zzju.zze())), zzbq.zza());
        } else {
            throw new GeneralSecurityException("Unknown KEM ID");
        }
        return zzjw.zza(zzke.zza(zzju, zzzc, num), zzze);
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzjz.zza();
            zzod zza2 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzc).zza());
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzd).zza(zzju.zzc.zza).zza(zzju.zzb.zzc).zza());
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zza).zza(zzju.zzc.zza).zza(zzju.zzb.zzb).zza());
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zzb).zza());
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzb).zza(zzju.zzc.zzb).zza(zzju.zzb.zzb).zza());
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zza).zza());
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", zzju.zzc().zza(zzju.zze.zza).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zzb).zza());
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", zzju.zzc().zza(zzju.zze.zzc).zza(zzju.zzf.zzc).zza(zzju.zzc.zzc).zza(zzju.zzb.zzb).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zzoc.zza().zza(zza);
            zzoc.zza().zza(zzb);
            zznv.zza().zza(zze, zzju.class);
            zzmt.zza().zza(zzc, true);
            zzmt.zza().zza(zzd, false);
            return;
        }
        throw new GeneralSecurityException("Registering HPKE Hybrid Encryption is not supported in FIPS mode");
    }
}
