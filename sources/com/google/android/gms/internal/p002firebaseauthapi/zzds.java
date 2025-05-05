package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdv;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzds  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzds {
    private static final zzor<zzdo, zzbg> zza = zzor.zza(new zzdr(), zzdo.class, zzbg.class);
    private static final zzbs<zzbg> zzb = zzna.zza("type.googleapis.com/google.crypto.tink.AesGcmKey", zzbg.class, zzvq.zzb.SYMMETRIC, zzsw.zze());
    private static final zznz<zzdv> zzc = new zzdu();
    private static final zznx<zzdv> zzd = new zzdt();
    private static final zzil.zza zze = zzil.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;

    static String zza() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public static /* synthetic */ zzdo zza(zzdv zzdv, Integer num) {
        if (zzdv.zzc() != 24) {
            return zzdo.zzb().zza(zzdv).zza(num).zza(zzze.zza(zzdv.zzc())).zza();
        }
        throw new GeneralSecurityException("192 bit AES GCM Parameters are not valid");
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        zzil.zza zza2 = zze;
        if (zza2.zza()) {
            zzgq.zza();
            zzoc.zza().zza(zza);
            zzod zza3 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_GCM", zzfh.zza);
            hashMap.put("AES128_GCM_RAW", zzdv.zze().zza(12).zzb(16).zzc(16).zza(zzdv.zzb.zzc).zza());
            hashMap.put("AES256_GCM", zzfh.zzb);
            hashMap.put("AES256_GCM_RAW", zzdv.zze().zza(12).zzb(32).zzc(16).zza(zzdv.zzb.zzc).zza());
            zza3.zza(Collections.unmodifiableMap(hashMap));
            zznw.zza().zza(zzc, zzdv.class);
            zznv.zza().zza(zzd, zzdv.class);
            zzmt.zza().zza(zzb, zza2, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }
}
