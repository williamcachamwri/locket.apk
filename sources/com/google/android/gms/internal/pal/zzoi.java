package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzoi extends zzpr {
    public zzoi() {
        super(zzvg.class, zzvj.class, new zzog(zzjx.class));
    }

    static /* bridge */ /* synthetic */ zzoy zzg(int i, int i2, int i3, int i4) {
        zzvc zza = zzvd.zza();
        zza.zzc(i);
        zza.zzb(i2);
        zza.zza(i3);
        zzuz zza2 = zzva.zza();
        zza2.zza((zzvd) zza.zzan());
        return new zzoy((zzva) zza2.zzan(), i4);
    }

    public final zzoz zza() {
        return new zzoh(this, zzva.class);
    }

    public final zzvn zzb() {
        return zzvn.ASYMMETRIC_PRIVATE;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzvg.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzvg zzvg = (zzvg) zzaef;
        if (zzvg.zzg().zzs()) {
            throw new GeneralSecurityException("Private key is empty.");
        } else if (zzvg.zzk()) {
            zzys.zzb(zzvg.zza(), 0);
            zzol.zza(zzvg.zzf().zzc());
        } else {
            throw new GeneralSecurityException("Missing public key.");
        }
    }
}
