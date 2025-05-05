package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzmj extends zzoz {
    final /* synthetic */ zzmk zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzmj(zzmk zzmk, Class cls) {
        super(cls);
        this.zza = zzmk;
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        zzwo zzc = zzwp.zzc();
        zzc.zza((zzws) zzaef);
        zzc.zzb(0);
        return (zzwp) zzc.zzan();
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzws.zze(zzaby, zzacm.zza());
    }

    public final /* bridge */ /* synthetic */ void zzd(zzaef zzaef) throws GeneralSecurityException {
        zzws zzws = (zzws) zzaef;
        if (zzws.zzf().isEmpty() || !zzws.zzg()) {
            throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
        }
    }
}
