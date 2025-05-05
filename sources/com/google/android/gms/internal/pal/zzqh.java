package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzqh extends zzpa {
    zzqh() {
        super(zzrm.class, new zzqf(zzkq.class));
    }

    /* access modifiers changed from: private */
    public static void zzm(zzrs zzrs) throws GeneralSecurityException {
        if (zzrs.zza() < 10) {
            throw new GeneralSecurityException("tag size too short");
        } else if (zzrs.zza() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* access modifiers changed from: private */
    public static void zzn(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    public final zzoz zza() {
        return new zzqg(this, zzrp.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzrm.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzrm zzrm = (zzrm) zzaef;
        zzys.zzb(zzrm.zza(), 0);
        zzn(zzrm.zzg().zzd());
        zzm(zzrm.zzf());
    }
}
