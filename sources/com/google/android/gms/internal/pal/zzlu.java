package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzlu extends zzpa {
    zzlu() {
        super(zzsk.class, new zzls(zzjt.class));
    }

    static /* bridge */ /* synthetic */ zzoy zzg(int i, int i2, int i3) {
        zzsm zzc = zzsn.zzc();
        zzc.zza(i);
        zzsp zzc2 = zzsq.zzc();
        zzc2.zza(16);
        zzc.zzb((zzsq) zzc2.zzan());
        return new zzoy((zzsn) zzc.zzan(), i3);
    }

    public final zzoz zza() {
        return new zzlt(this, zzsn.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzsk.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzsk zzsk = (zzsk) zzaef;
        zzys.zzb(zzsk.zza(), 0);
        zzys.zza(zzsk.zzg().zzd());
        if (zzsk.zzf().zza() != 12 && zzsk.zzf().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
