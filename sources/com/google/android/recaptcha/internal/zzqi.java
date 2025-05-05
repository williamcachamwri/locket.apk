package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzqi extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzqi zzb;
    private static volatile zzos zzd;
    private int zze;
    private zzmn zzf;
    private int zzg;

    static {
        zzqi zzqi = new zzqi();
        zzb = zzqi;
        zznf.zzI(zzqi.class, zzqi);
    }

    private zzqi() {
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u0004", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzqi();
        } else {
            if (i2 == 4) {
                return new zzqg((zzqh) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzqi.class) {
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
