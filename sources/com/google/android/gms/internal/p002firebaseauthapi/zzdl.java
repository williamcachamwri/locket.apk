package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdm;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzdl {
    private static final zzor<zzdh, zzbg> zza = zzor.zza(new zzdk(), zzdh.class, zzbg.class);
    private static final zzbs<zzbg> zzb = zzna.zza("type.googleapis.com/google.crypto.tink.AesEaxKey", zzbg.class, zzvq.zzb.SYMMETRIC, zzsn.zzf());
    private static final zznx<zzdm> zzc = new zzdn();

    static String zza() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public static /* synthetic */ zzdh zza(zzdm zzdm, Integer num) {
        if (zzdm.zzc() != 24) {
            return zzdh.zzb().zza(zzdm).zza(num).zza(zzze.zza(zzdm.zzc())).zza();
        }
        throw new GeneralSecurityException("192 bit AES GCM Parameters are not valid");
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzgi.zza();
            zzoc.zza().zza(zza);
            zzod zza2 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_EAX", zzfh.zzc);
            hashMap.put("AES128_EAX_RAW", zzdm.zze().zza(16).zzb(16).zzc(16).zza(zzdm.zza.zzc).zza());
            hashMap.put("AES256_EAX", zzfh.zzd);
            hashMap.put("AES256_EAX_RAW", zzdm.zze().zza(16).zzb(32).zzc(16).zza(zzdm.zza.zzc).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zznv.zza().zza(zzc, zzdm.class);
            zzmt.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES EAX is not supported in FIPS mode");
    }
}
