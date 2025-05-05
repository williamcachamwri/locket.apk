package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzme extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzme zzb;
    private static volatile zzos zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private String zzh = "";
    private int zzi;

    static {
        zzme zzme = new zzme();
        zzb = zzme;
        zznf.zzI(zzme.class, zzme);
    }

    private zzme() {
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zznj zznj = zzlr.zza;
            zznj zznj2 = zzlr.zza;
            return new zzow(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003ဈ\u0002\u0004᠌\u0003", new Object[]{"zze", "zzf", zznj, "zzg", zznj2, "zzh", "zzi", zznj2});
        } else if (i2 == 3) {
            return new zzme();
        } else {
            if (i2 == 4) {
                return new zzmd((zzmj) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzme.class) {
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
