package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzmg extends zzpa {
    zzmg() {
        super(zzwj.class, new zzme(zzjt.class));
    }

    public final zzoz zza() {
        return new zzmf(this, zzwm.class);
    }

    public final zzvn zzb() {
        return zzvn.REMOTE;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzwj.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzys.zzb(((zzwj) zzaef).zza(), 0);
    }
}
