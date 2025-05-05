package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzrb extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzrb zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private int zzg;
    private String zzh = "";
    private int zzi;
    private String zzj = "";
    private int zzk;

    static {
        zzrb zzrb = new zzrb();
        zzb = zzrb;
        zznf.zzI(zzrb.class, zzrb);
    }

    private zzrb() {
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001\u0004\u0002Ȉ\u0003\u0004\u0004Ȉ\u0005\u0004\u0006Ȉ\u0007\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i2 == 3) {
            return new zzrb();
        } else {
            if (i2 == 4) {
                return new zzqz((zzra) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzrb.class) {
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
