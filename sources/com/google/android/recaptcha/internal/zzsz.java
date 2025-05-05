package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsz extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsz zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private zznk zzh = zzy();
    private int zzi;
    private int zzj;

    static {
        zzsz zzsz = new zzsz();
        zzb = zzsz;
        zznf.zzI(zzsz.class, zzsz);
    }

    private zzsz() {
    }

    public static zzsx zzf() {
        return (zzsx) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzsz zzsz, String str) {
        str.getClass();
        zzsz.zze |= 2;
        zzsz.zzg = str;
    }

    static /* synthetic */ void zzj(zzsz zzsz, String str) {
        str.getClass();
        zzsz.zze |= 1;
        zzsz.zzf = str;
    }

    static /* synthetic */ void zzk(zzsz zzsz, int i) {
        zzsz.zzi = 2;
        zzsz.zze |= 4;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003'\u0004᠌\u0002\u0005င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzsy.zza, "zzj"});
        } else if (i2 == 3) {
            return new zzsz();
        } else {
            if (i2 == 4) {
                return new zzsx((zztc) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsz.class) {
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
