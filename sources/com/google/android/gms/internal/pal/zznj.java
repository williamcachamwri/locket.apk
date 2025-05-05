package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zznj extends zzoz {
    final /* synthetic */ zznk zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zznj(zznk zznk, Class cls) {
        super(cls);
        this.zza = zznk;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zztw zztw = (zztw) zzaef;
        KeyPair zzc = zzxx.zzc(zzxx.zzk(zznt.zzc(zztw.zze().zzf().zzg())));
        ECPoint w = ((ECPublicKey) zzc.getPublic()).getW();
        zzue zzd = zzuf.zzd();
        zzd.zzb(0);
        zzd.zza(zztw.zze());
        zzd.zzc(zzaby.zzn(w.getAffineX().toByteArray()));
        zzd.zzd(zzaby.zzn(w.getAffineY().toByteArray()));
        zzub zzc2 = zzuc.zzc();
        zzc2.zzc(0);
        zzc2.zzb((zzuf) zzd.zzan());
        zzc2.zza(zzaby.zzn(((ECPrivateKey) zzc.getPrivate()).getS().toByteArray()));
        return (zzuc) zzc2.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zztw.zzd(zzaby, zzacm.zza());
    }

    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zznk.zzh(4, 5, 3, zzkl.zza("AES128_GCM"), zznk.zza, 1));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zznk.zzh(4, 5, 3, zzkl.zza("AES128_GCM"), zznk.zza, 3));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zznk.zzh(4, 5, 4, zzkl.zza("AES128_GCM"), zznk.zza, 1));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zznk.zzh(4, 5, 4, zzkl.zza("AES128_GCM"), zznk.zza, 3));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zznk.zzh(4, 5, 4, zzkl.zza("AES128_GCM"), zznk.zza, 3));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zznk.zzh(4, 5, 3, zzkl.zza("AES128_CTR_HMAC_SHA256"), zznk.zza, 1));
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zznk.zzh(4, 5, 3, zzkl.zza("AES128_CTR_HMAC_SHA256"), zznk.zza, 3));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zznk.zzh(4, 5, 4, zzkl.zza("AES128_CTR_HMAC_SHA256"), zznk.zza, 1));
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zznk.zzh(4, 5, 4, zzkl.zza("AES128_CTR_HMAC_SHA256"), zznk.zza, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    public final /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zznt.zza(((zztw) zzaef).zze());
    }
}
