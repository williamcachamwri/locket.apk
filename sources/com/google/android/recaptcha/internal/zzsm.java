package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsm extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsm zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzsm zzsm = new zzsm();
        zzb = zzsm;
        zznf.zzI(zzsm.class, zzsm);
    }

    private zzsm() {
    }

    public static zzsl zzf() {
        return (zzsl) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzsm zzsm, String str) {
        str.getClass();
        zzsm.zze |= 2;
        zzsm.zzg = str;
    }

    static /* synthetic */ void zzj(zzsm zzsm, String str) {
        str.getClass();
        zzsm.zze |= 1;
        zzsm.zzf = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzsm();
        } else {
            if (i2 == 4) {
                return new zzsl((zzsp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsm.class) {
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
