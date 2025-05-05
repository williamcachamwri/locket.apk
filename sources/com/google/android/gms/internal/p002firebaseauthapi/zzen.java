package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzil;
import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzen  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzen {
    private static final zzbs<zzbg> zza = zzna.zza("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey", zzbg.class, zzvq.zzb.SYMMETRIC, zzwm.zze());
    private static final zznx<zzfa> zzb = new zzeq();
    private static final zzor<zzey, zzbg> zzc = zzor.zza(new zzep(), zzey.class, zzbg.class);

    public static void zza(boolean z) throws GeneralSecurityException {
        if (zzil.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzfb.zza();
            zznv.zza().zza(zzb, zzfa.class);
            zzoc.zza().zza(zzc);
            zzmt.zza().zza(zza, true);
            return;
        }
        throw new GeneralSecurityException("Registering KMS Envelope AEAD is not supported in FIPS mode");
    }
}
