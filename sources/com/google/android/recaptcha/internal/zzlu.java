package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzlu extends zznc implements zzol {
    /* access modifiers changed from: private */
    public static final zzlu zzd;
    private static volatile zzos zze;
    private int zzf;
    private boolean zzg;
    private zzmc zzh;
    private boolean zzi;
    private zzme zzj;
    private zznm zzk = zzov.zze();
    private byte zzl = 2;

    static {
        zzlu zzlu = new zzlu();
        zzd = zzlu;
        zznf.zzI(zzlu.class, zzlu);
    }

    private zzlu() {
    }

    public static zzlu zzg() {
        return zzd;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzl);
        }
        if (i2 == 2) {
            return new zzow(zzd, "\u0001\u0005\u0000\u0001\u0001ϧ\u0005\u0000\u0001\u0002\u0001ဇ\u0000\u0002ᐉ\u0001\u0003ဇ\u0002\u0004ဉ\u0003ϧЛ", new Object[]{"zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzmi.class});
        } else if (i2 == 3) {
            return new zzlu();
        } else {
            if (i2 == 4) {
                return new zzlt((zzmj) null);
            }
            if (i2 == 5) {
                return zzd;
            }
            if (i2 != 6) {
                this.zzl = obj == null ? (byte) 0 : 1;
                return null;
            }
            zzos zzos = zze;
            if (zzos == null) {
                synchronized (zzlu.class) {
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
