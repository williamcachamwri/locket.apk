package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzok extends zzpa {
    public zzok() {
        super(zzvj.class, new zzoj(zzjy.class));
    }

    public final zzvn zzb() {
        return zzvn.ASYMMETRIC_PUBLIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzvj.zzg(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HpkePublicKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzvj zzvj = (zzvj) zzaef;
        zzys.zzb(zzvj.zza(), 0);
        if (zzvj.zzl()) {
            zzol.zza(zzvj.zzc());
            return;
        }
        throw new GeneralSecurityException("Missing HPKE key params.");
    }
}
