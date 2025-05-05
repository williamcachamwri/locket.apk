package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzpx;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpu {
    private static final zznx<zzpx> zza = new zzpt();
    private static final zzor<zzpq, zzqa> zzb = zzor.zza(new zzpw(), zzpq.class, zzqa.class);
    private static final zzor<zzpq, zzci> zzc = zzor.zza(new zzpv(), zzpq.class, zzci.class);
    private static final zzbs<zzci> zzd = zzna.zza("type.googleapis.com/google.crypto.tink.AesCmacKey", zzci.class, zzvq.zzb.SYMMETRIC, zzrp.zzf());

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzra.zza();
            zznv.zza().zza(zza, zzpx.class);
            zzoc.zza().zza(zzb);
            zzoc.zza().zza(zzc);
            zzod zza2 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES_CMAC", zzqu.zzc);
            hashMap.put("AES256_CMAC", zzqu.zzc);
            hashMap.put("AES256_CMAC_RAW", zzpx.zzd().zza(32).zzb(16).zza(zzpx.zzb.zzd).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zzmt.zza().zza(zzd, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES CMAC is not supported in FIPS mode");
    }

    /* access modifiers changed from: private */
    public static void zza(zzpx zzpx) throws GeneralSecurityException {
        if (zzpx.zzc() != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }
}
