package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzek  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzek {
    private static final zzor<zzes, zzbg> zza = zzor.zza(new zzem(), zzes.class, zzbg.class);
    private static final zzbs<zzbg> zzb = zzna.zza("type.googleapis.com/google.crypto.tink.KmsAeadKey", zzbg.class, zzvq.zzb.REMOTE, zzwg.zze());
    private static final zznx<zzer> zzc = new zzel();

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzet.zza();
            zzoc.zza().zza(zza);
            zznv.zza().zza(zzc, zzer.class);
            zzmt.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering KMS AEAD is not supported in FIPS mode");
    }
}
