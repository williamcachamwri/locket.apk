package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzqr extends zzpa {
    public zzqr() {
        super(zzup.class, new zzqp(zzkq.class));
    }

    public static final void zzh(zzup zzup) throws GeneralSecurityException {
        zzys.zzb(zzup.zza(), 0);
        if (zzup.zzh().zzd() >= 16) {
            zzn(zzup.zzg());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    static /* bridge */ /* synthetic */ zzoy zzm(int i, int i2, int i3, int i4) {
        zzur zzc = zzus.zzc();
        zzuu zzc2 = zzuv.zzc();
        zzc2.zzb(i3);
        zzc2.zza(i2);
        zzc.zzb((zzuv) zzc2.zzan());
        zzc.zza(i);
        return new zzoy((zzus) zzc.zzan(), i4);
    }

    /* access modifiers changed from: private */
    public static void zzn(zzuv zzuv) throws GeneralSecurityException {
        if (zzuv.zza() >= 10) {
            int zzg = zzuv.zzg() - 2;
            if (zzg != 1) {
                if (zzg != 2) {
                    if (zzg != 3) {
                        if (zzg != 4) {
                            if (zzg != 5) {
                                throw new GeneralSecurityException("unknown hash type");
                            } else if (zzuv.zza() > 28) {
                                throw new GeneralSecurityException("tag size too big");
                            }
                        } else if (zzuv.zza() > 64) {
                            throw new GeneralSecurityException("tag size too big");
                        }
                    } else if (zzuv.zza() > 32) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                } else if (zzuv.zza() > 48) {
                    throw new GeneralSecurityException("tag size too big");
                }
            } else if (zzuv.zza() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("tag size too small");
        }
    }

    public final zzoz zza() {
        return new zzqq(this, zzus.class);
    }

    public final zzvn zzb() {
        return zzvn.SYMMETRIC;
    }

    public final /* synthetic */ zzaef zzc(zzaby zzaby) throws zzadi {
        return zzup.zzf(zzaby, zzacm.zza());
    }

    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public final /* bridge */ /* synthetic */ void zze(zzaef zzaef) throws GeneralSecurityException {
        zzh((zzup) zzaef);
    }

    public final int zzf() {
        return 2;
    }
}
