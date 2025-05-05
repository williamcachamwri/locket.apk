package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzmc extends zznc implements zzol {
    /* access modifiers changed from: private */
    public static final zzmc zzd;
    private static volatile zzos zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private byte zzm = 2;

    static {
        zzmc zzmc = new zzmc();
        zzd = zzmc;
        zznf.zzI(zzmc.class, zzmc);
    }

    private zzmc() {
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzm);
        }
        if (i2 == 2) {
            return new zzow(zzd, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005", new Object[]{"zzf", "zzg", zzlx.zza, "zzh", zzlw.zza, "zzi", zzma.zza, "zzj", zzmb.zza, "zzk", zzlz.zza, "zzl", zzly.zza});
        } else if (i2 == 3) {
            return new zzmc();
        } else {
            if (i2 == 4) {
                return new zzlv((zzmj) null);
            }
            if (i2 == 5) {
                return zzd;
            }
            if (i2 != 6) {
                this.zzm = obj == null ? (byte) 0 : 1;
                return null;
            }
            zzos zzos = zze;
            if (zzos == null) {
                synchronized (zzmc.class) {
                    zzos = zze;
                    if (zzos == null) {
                        zzos = new zzna(zzd);
                        zze = zzos;
                    }
                }
            }
            return zzos;
        }
    }
}
