package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zznk extends zzpr {
    /* access modifiers changed from: private */
    public static final byte[] zza = new byte[0];

    zznk() {
        super(zzuc.class, zzuf.class, new zzni(zzjx.class));
    }

    static /* bridge */ /* synthetic */ zzoy zzh(int i, int i2, int i3, zzkk zzkk, byte[] bArr, int i4) {
        zztv zza2 = zztw.zza();
        zzuh zza3 = zzui.zza();
        int i5 = 4;
        zza3.zzb(4);
        zza3.zzc(5);
        zza3.zza(zzaby.zzn(bArr));
        zzui zzui = (zzui) zza3.zzan();
        zzvs zza4 = zzvt.zza();
        zza4.zza(zzkk.zza());
        zza4.zzb(zzaby.zzn(zzkk.zzb()));
        int zzc = zzkk.zzc() - 1;
        if (zzc == 0) {
            i5 = 3;
        } else if (zzc != 1) {
            i5 = zzc != 2 ? 6 : 5;
        }
        zza4.zzc(i5);
        zzts zza5 = zztt.zza();
        zza5.zza((zzvt) zza4.zzan());
        zzty zzc2 = zztz.zzc();
        zzc2.zzb(zzui);
        zzc2.zza((zztt) zza5.zzan());
        zzc2.zzc(i3);
        zza2.zza((zztz) zzc2.zzan());
        return new zzoy((zztw) zza2.zzan(), i4);
    }

    public final zzoz zza() {
        return new zznj(this, zztw.class);
    }

    public final zzvn zzb() {
        return zzvn.ASYMMETRIC_PRIVATE;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzuc.zze(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzuc zzuc = (zzuc) zzaef;
        if (!zzuc.zzg().zzs()) {
            zzys.zzb(zzuc.zza(), 0);
            zznt.zza(zzuc.zzf().zzc());
            return;
        }
        throw new GeneralSecurityException("invalid ECIES private key");
    }
}
