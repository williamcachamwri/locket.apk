package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzte extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzte zzb;
    private static volatile zzos zzd;
    private String zze = "";
    private String zzf = "";
    private String zzg = "";

    static {
        zzte zzte = new zzte();
        zzb = zzte;
        zznf.zzI(zzte.class, zzte);
    }

    private zzte() {
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzte();
        } else {
            if (i2 == 4) {
                return new zztd((zztl) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzte.class) {
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
