package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzmk extends zzpa {
    zzmk() {
        super(zzwp.class, new zzmi(zzjt.class));
    }

    public final zzoz zza() {
        return new zzmj(this, zzws.class);
    }

    public final zzvn zzb() {
        return zzvn.REMOTE;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzwp.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzys.zzb(((zzwp) zzaef).zza(), 0);
    }
}
