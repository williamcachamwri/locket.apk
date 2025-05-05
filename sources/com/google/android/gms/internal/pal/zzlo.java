package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzlo extends zzpa {
    zzlo() {
        super(zzrv.class, new zzlm(zzjt.class));
    }

    static /* bridge */ /* synthetic */ zzoy zzg(int i, int i2, int i3, int i4, int i5, int i6) {
        zzsd zzc = zzse.zzc();
        zzsg zzc2 = zzsh.zzc();
        zzc2.zza(16);
        zzc.zzb((zzsh) zzc2.zzan());
        zzc.zza(i);
        zzur zzc3 = zzus.zzc();
        zzuu zzc4 = zzuv.zzc();
        zzc4.zzb(5);
        zzc4.zza(i4);
        zzc3.zzb((zzuv) zzc4.zzan());
        zzc3.zza(32);
        zzrx zza = zzry.zza();
        zza.zza((zzse) zzc.zzan());
        zza.zzb((zzus) zzc3.zzan());
        return new zzoy((zzry) zza.zzan(), i6);
    }

    public final zzoz zza() {
        return new zzln(this, zzry.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzrv.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzrv zzrv = (zzrv) zzaef;
        zzys.zzb(zzrv.zza(), 0);
        new zzlr();
        zzlr.zzh(zzrv.zzf());
        new zzqr();
        zzqr.zzh(zzrv.zzg());
    }

    public final int zzf() {
        return 2;
    }
}
