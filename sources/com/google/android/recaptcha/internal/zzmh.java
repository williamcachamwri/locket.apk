package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzmh extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzmh zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private boolean zzg;
    private byte zzh = 2;

    static {
        zzmh zzmh = new zzmh();
        zzb = zzmh;
        zznf.zzI(zzmh.class, zzmh);
    }

    private zzmh() {
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzh);
        }
        if (i2 == 2) {
            return new zzow(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔈ\u0000\u0002ᔇ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzmh();
        } else {
            if (i2 == 4) {
                return new zzmg((zzmj) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                this.zzh = obj == null ? (byte) 0 : 1;
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzmh.class) {
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
