package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsc extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsc zzb;
    private static volatile zzos zzd;
    private zzlg zze = zzlg.zzb;

    static {
        zzsc zzsc = new zzsc();
        zzb = zzsc;
        zznf.zzI(zzsc.class, zzsc);
    }

    private zzsc() {
    }

    public static zzsc zzi() {
        return zzb;
    }

    public final zzlg zzf() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zzsc();
        } else {
            if (i2 == 4) {
                return new zzsb((zzsp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsc.class) {
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
