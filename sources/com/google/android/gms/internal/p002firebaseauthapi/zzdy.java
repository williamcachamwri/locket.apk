package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzec;
import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzdy {
    private static final zzor<zzdx, zzbg> zza = zzor.zza(new zzeb(), zzdx.class, zzbg.class);
    private static final zznx<zzec> zzb = new zzea();
    private static final zznz<zzec> zzc = new zzed();
    private static final zzbs<zzbg> zzd = zzna.zza("type.googleapis.com/google.crypto.tink.AesGcmSivKey", zzbg.class, zzvq.zzb.SYMMETRIC, zztc.zze());

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzgw.zza();
            if (zza()) {
                zzoc.zza().zza(zza);
                zzod zza2 = zzod.zza();
                HashMap hashMap = new HashMap();
                hashMap.put("AES128_GCM_SIV", zzec.zzc().zza(16).zza(zzec.zza.zza).zza());
                hashMap.put("AES128_GCM_SIV_RAW", zzec.zzc().zza(16).zza(zzec.zza.zzc).zza());
                hashMap.put("AES256_GCM_SIV", zzec.zzc().zza(32).zza(zzec.zza.zza).zza());
                hashMap.put("AES256_GCM_SIV_RAW", zzec.zzc().zza(32).zza(zzec.zza.zzc).zza());
                zza2.zza(Collections.unmodifiableMap(hashMap));
                zznw.zza().zza(zzc, zzec.class);
                zznv.zza().zza(zzb, zzec.class);
                zzmt.zza().zza(zzd, true);
                return;
            }
            return;
        }
        throw new GeneralSecurityException("Registering AES GCM SIV is not supported in FIPS mode");
    }

    private static boolean zza() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        }
    }
}
