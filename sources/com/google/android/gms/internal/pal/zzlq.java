package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzlq extends zzoz {
    final /* synthetic */ zzlr zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzlq(zzlr zzlr, Class cls) {
        super(cls);
        this.zza = zzlr;
    }

    public static final zzsb zzf(zzse zzse) throws GeneralSecurityException {
        zzsa zzc = zzsb.zzc();
        zzc.zzb(zzse.zzg());
        zzc.zza(zzaby.zzn(zzyq.zza(zzse.zza())));
        zzc.zzc(0);
        return (zzsb) zzc.zzan();
    }

    public final /* bridge */ /* synthetic */ zzaef zza(zzaef zzaef) throws GeneralSecurityException {
        return zzf((zzse) zzaef);
    }

    public final /* synthetic */ zzaef zzb(zzaby zzaby) throws zzadi {
        return zzse.zzf(zzaby, zzacm.zza());
    }

    /* renamed from: zze */
    public final void zzd(zzse zzse) throws GeneralSecurityException {
        zzys.zza(zzse.zza());
        zzlr.zzm(zzse.zzg());
    }
}
