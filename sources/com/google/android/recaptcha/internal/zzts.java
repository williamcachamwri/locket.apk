package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzts extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzts zzb;
    private static volatile zzos zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzts zzts = new zzts();
        zzb = zzts;
        zznf.zzI(zzts.class, zzts);
    }

    private zzts() {
    }

    public static zztr zzf() {
        return (zztr) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzts zzts, String str) {
        str.getClass();
        zzts.zze = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zze", "zzf"});
        } else if (i2 == 3) {
            return new zzts();
        } else {
            if (i2 == 4) {
                return new zztr((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzts.class) {
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
