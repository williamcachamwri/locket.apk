package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzdd {
    private static final zzor<zzcz, zzbg> zza = zzor.zza(new zzdc(), zzcz.class, zzbg.class);
    private static final zzbs<zzbg> zzb = zzna.zza("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey", zzbg.class, zzvq.zzb.SYMMETRIC, zzry.zzf());
    private static final zznz<zzdg> zzc = new zzdf();
    private static final zznx<zzdg> zzd = new zzde();
    private static final zzil.zza zze = zzil.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;

    static String zza() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    static zzcz zza(zzdg zzdg, @Nullable Integer num) throws GeneralSecurityException {
        if (zzdg.zzb() == 16 || zzdg.zzb() == 32) {
            return zzcz.zzb().zza(zzdg).zza(num).zza(zzze.zza(zzdg.zzb())).zzb(zzze.zza(zzdg.zzc())).zza();
        }
        throw new GeneralSecurityException("AES key size must be 16 or 32 bytes");
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        zzil.zza zza2 = zze;
        if (zza2.zza()) {
            zzgc.zza();
            zzoc.zza().zza(zza);
            zzod zza3 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_CTR_HMAC_SHA256", zzfh.zze);
            hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza());
            hashMap.put("AES256_CTR_HMAC_SHA256", zzfh.zzf);
            hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzdg.zzf().zza(32).zzb(32).zzd(32).zzc(16).zza(zzdg.zzc.zzc).zza(zzdg.zzb.zzc).zza());
            zza3.zza(Collections.unmodifiableMap(hashMap));
            zznw.zza().zza(zzc, zzdg.class);
            zznv.zza().zza(zzd, zzdg.class);
            zzmt.zza().zza(zzb, zza2, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CTR-HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }
}
