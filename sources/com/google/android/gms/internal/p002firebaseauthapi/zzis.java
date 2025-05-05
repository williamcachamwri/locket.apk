package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zziv;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzis  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzis {
    private static final zzor<zzio, zzbl> zza = zzor.zza(new zzir(), zzio.class, zzbl.class);
    private static final zzbs<zzbl> zzb = zzna.zza("type.googleapis.com/google.crypto.tink.AesSivKey", zzbl.class, zzvq.zzb.SYMMETRIC, zzti.zze());
    private static final zznz<zziv> zzc = new zziu();
    private static final zznx<zziv> zzd = new zzit();

    static zzio zza(zziv zziv, @Nullable Integer num) throws GeneralSecurityException {
        zza(zziv);
        return zzio.zzb().zza(zziv).zza(num).zza(zzze.zza(zziv.zzb())).zza();
    }

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzjg.zza();
            zzoc.zza().zza(zza);
            zzod zza2 = zzod.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES256_SIV", zzje.zza);
            hashMap.put("AES256_SIV_RAW", zziv.zzc().zza(64).zza(zziv.zzb.zzc).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zznw.zza().zza(zzc, zziv.class);
            zznv.zza().zza(zzd, zziv.class);
            zzmt.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES SIV is not supported in FIPS mode");
    }

    /* access modifiers changed from: private */
    public static void zza(zziv zziv) throws GeneralSecurityException {
        if (zziv.zzb() != 64) {
            throw new InvalidAlgorithmParameterException("invalid key size: " + zziv.zzb() + ". Valid keys must have 64 bytes.");
        }
    }
}
