package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzlr extends zzpa {
    zzlr() {
        super(zzsb.class, new zzlp(zzyk.class));
    }

    public static final void zzh(zzsb zzsb) throws GeneralSecurityException {
        zzys.zzb(zzsb.zza(), 0);
        zzys.zza(zzsb.zzh().zzd());
        zzm(zzsb.zzg());
    }

    /* access modifiers changed from: private */
    public static final void zzm(zzsh zzsh) throws GeneralSecurityException {
        if (zzsh.zza() < 12 || zzsh.zza() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    public final zzoz zza() {
        return new zzlq(this, zzse.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzsb.zzf(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzh((zzsb) zzaef);
    }
}
