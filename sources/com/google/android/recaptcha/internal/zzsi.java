package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsi extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsi zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";

    static {
        zzsi zzsi = new zzsi();
        zzb = zzsi;
        zznf.zzI(zzsi.class, zzsi);
    }

    private zzsi() {
    }

    public static zzsh zzf() {
        return (zzsh) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzsi zzsi, String str) {
        zzsi.zze |= 1;
        zzsi.zzf = str;
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
            return new zzsi();
        } else {
            if (i2 == 4) {
                return new zzsh((zzsp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsi.class) {
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
