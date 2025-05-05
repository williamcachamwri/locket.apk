package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzmi extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzmi zzb;
    private static volatile zzos zzd;
    private int zze;
    private zznm zzf = zzov.zze();
    private String zzg = "";
    private long zzh;
    private long zzi;
    private double zzj;
    private zzlg zzk = zzlg.zzb;
    private String zzl = "";
    private byte zzm = 2;

    static {
        zzmi zzmi = new zzmi();
        zzb = zzmi;
        zznf.zzI(zzmi.class, zzmi);
    }

    private zzmi() {
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzm);
        }
        if (i2 == 2) {
            return new zzow(zzb, "\u0001\u0007\u0000\u0001\u0002\b\u0007\u0000\u0001\u0001\u0002Л\u0003ဈ\u0000\u0004ဃ\u0001\u0005ဂ\u0002\u0006က\u0003\u0007ည\u0004\bဈ\u0005", new Object[]{"zze", "zzf", zzmh.class, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        } else if (i2 == 3) {
            return new zzmi();
        } else {
            if (i2 == 4) {
                return new zzmf((zzmj) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                this.zzm = obj == null ? (byte) 0 : 1;
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzmi.class) {
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
