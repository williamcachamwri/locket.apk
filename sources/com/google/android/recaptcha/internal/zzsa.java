package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsa extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsa zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";

    static {
        zzsa zzsa = new zzsa();
        zzb = zzsa;
        zznf.zzI(zzsa.class, zzsa);
    }

    private zzsa() {
    }

    public static zzrz zzf() {
        return (zzrz) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzsa zzsa, String str) {
        zzsa.zze |= 1;
        zzsa.zzf = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ለ\u0000", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzsa();
        } else {
            if (i2 == 4) {
                return new zzrz((zzsp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsa.class) {
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
