package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzne extends zzpa {
    zzne() {
        super(zztf.class, new zznc(zzjw.class));
    }

    public final zzoz zza() {
        return new zznd(this, zzti.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zztf.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zztf zztf = (zztf) zzaef;
        zzys.zzb(zztf.zza(), 0);
        if (zztf.zzf().zzd() != 64) {
            int zzd = zztf.zzf().zzd();
            throw new InvalidKeyException("invalid key size: " + zzd + ". Valid keys must have 64 bytes.");
        }
    }
}
