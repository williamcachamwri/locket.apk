package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import com.google.android.gms.internal.p002firebaseauthapi.zzdv;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzjp;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzjl {
    private static final zzor<zzjs, zzbo> zza = zzor.zza(new zzjo(), zzjs.class, zzbo.class);
    private static final zzor<zzjv, zzbn> zzb = zzor.zza(new zzjn(), zzjv.class, zzbn.class);
    private static final zzcj<zzbo> zzc = zzna.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", zzbo.class, zzug.zzf());
    private static final zzbs<zzbn> zzd = zzna.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", zzbn.class, zzvq.zzb.ASYMMETRIC_PUBLIC, zzuj.zzh());
    private static final zznx<zzjp> zze = new zzjq();

    static String zza() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public static /* synthetic */ zzjs zza(zzjp zzjp, Integer num) {
        ECParameterSpec eCParameterSpec;
        zzjp.zzc zzd2 = zzjp.zzd();
        if (zzd2 == zzjp.zzc.zza) {
            eCParameterSpec = zzmk.zza;
        } else if (zzd2 == zzjp.zzc.zzb) {
            eCParameterSpec = zzmk.zzb;
        } else if (zzd2 == zzjp.zzc.zzc) {
            eCParameterSpec = zzmk.zzc;
        } else {
            throw new GeneralSecurityException("Unsupported curve type: " + String.valueOf(zzd2));
        }
        KeyPair zza2 = zzyb.zza(eCParameterSpec);
        return zzjs.zza(zzjv.zza(zzjp, ((ECPublicKey) zza2.getPublic()).getW(), num), zzzf.zza(((ECPrivateKey) zza2.getPrivate()).getS(), zzbq.zza()));
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzkz.zza();
            zzod zza2 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zzb).zza(zzjp.zzd.zza).zza((zzch) zzdv.zze().zza(12).zzb(16).zzc(16).zza(zzdv.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zzb).zza(zzjp.zzd.zzc).zza((zzch) zzdv.zze().zza(12).zzb(16).zzc(16).zza(zzdv.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zza).zza(zzjp.zzd.zza).zza((zzch) zzdv.zze().zza(12).zzb(16).zzc(16).zza(zzdv.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zza).zza(zzjp.zzd.zzc).zza((zzch) zzdv.zze().zza(12).zzb(16).zzc(16).zza(zzdv.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zza).zza(zzjp.zzd.zzc).zza((zzch) zzdv.zze().zza(12).zzb(16).zzc(16).zza(zzdv.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zzb).zza(zzjp.zzd.zza).zza((zzch) zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zzb).zza(zzjp.zzd.zzc).zza((zzch) zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zza).zza(zzjp.zzd.zza).zza((zzch) zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza()).zza());
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjp.zzc().zza(zzjp.zzc.zza).zza(zzjp.zzb.zzc).zza(zzjp.zze.zza).zza(zzjp.zzd.zzc).zza((zzch) zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza()).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zzoc.zza().zza(zza);
            zzoc.zza().zza(zzb);
            zznv.zza().zza(zze, zzjp.class);
            zzmt.zza().zza(zzc, true);
            zzmt.zza().zza(zzd, false);
            return;
        }
        throw new GeneralSecurityException("Registering ECIES Hybrid Encryption is not supported in FIPS mode");
    }
}
