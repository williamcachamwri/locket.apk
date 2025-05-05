package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zznm extends zzpa {
    public zznm() {
        super(zzuf.class, new zznl(zzjy.class));
    }

    public final zzvn zzb() {
        return zzvn.ASYMMETRIC_PUBLIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzuf.zzg(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzuf zzuf = (zzuf) zzaef;
        zzys.zzb(zzuf.zza(), 0);
        zznt.zza(zzuf.zzc());
    }
}
