package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzlx extends zzpa {
    zzlx() {
        super(zzst.class, new zzlv(zzjt.class));
    }

    static /* bridge */ /* synthetic */ zzoy zzg(int i, int i2) {
        zzsv zzc = zzsw.zzc();
        zzc.zza(i);
        return new zzoy((zzsw) zzc.zzan(), i2);
    }

    public final zzoz zza() {
        return new zzlw(this, zzsw.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzst.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzst zzst = (zzst) zzaef;
        zzys.zzb(zzst.zza(), 0);
        zzys.zza(zzst.zzf().zzd());
    }

    public final int zzf() {
        return 2;
    }
}
