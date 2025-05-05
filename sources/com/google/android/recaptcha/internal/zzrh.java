package com.google.android.recaptcha.internal;

import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzrh extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzrh zzb;
    private static volatile zzos zzd;
    private int zze;
    private zznm zzf = zzB();
    private zznm zzg = zzB();
    private zzqp zzh;

    static {
        zzrh zzrh = new zzrh();
        zzb = zzrh;
        zznf.zzI(zzrh.class, zzrh);
    }

    private zzrh() {
    }

    static /* synthetic */ void zzM(zzrh zzrh, zzre zzre) {
        zzre.getClass();
        zznm zznm = zzrh.zzf;
        if (!zznm.zzc()) {
            zzrh.zzf = zznf.zzC(zznm);
        }
        zzrh.zzf.add(zzre);
    }

    static /* synthetic */ void zzN(zzrh zzrh, zzrt zzrt) {
        zzrt.getClass();
        zznm zznm = zzrh.zzg;
        if (!zznm.zzc()) {
            zzrh.zzg = zznf.zzC(zznm);
        }
        zzrh.zzg.add(zzrt);
    }

    public static zzrf zzi() {
        return (zzrf) zzb.zzq();
    }

    public static zzrh zzk(byte[] bArr) throws zznp {
        return (zzrh) zznf.zzx(zzb, bArr);
    }

    public final int zzf() {
        return this.zzf.size();
    }

    public final int zzg() {
        return this.zzg.size();
    }

    public final List zzl() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003ဉ\u0000", new Object[]{"zze", "zzf", zzre.class, "zzg", zzrt.class, "zzh"});
        } else if (i2 == 3) {
            return new zzrh();
        } else {
            if (i2 == 4) {
                return new zzrf((zzrg) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzrh.class) {
                    zzos = zzd;
                    if (zzos == null) {
                        zzos = new zzna(zzb);
                        zzd = zzos;
                    }
                }
            }
            return zzos;
        }
    }
}
