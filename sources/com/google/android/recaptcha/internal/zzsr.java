package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsr extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsr zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private String zzl = "";
    private zzsk zzm;

    static {
        zzsr zzsr = new zzsr();
        zzb = zzsr;
        zznf.zzI(zzsr.class, zzsr);
    }

    private zzsr() {
    }

    static /* synthetic */ void zzM(zzsr zzsr, String str) {
        str.getClass();
        zzsr.zze |= 4;
        zzsr.zzh = str;
    }

    public static zzsq zzf() {
        return (zzsq) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzsr zzsr, String str) {
        str.getClass();
        zzsr.zze |= 8;
        zzsr.zzi = str;
    }

    static /* synthetic */ void zzj(zzsr zzsr, String str) {
        str.getClass();
        zzsr.zze |= 2;
        zzsr.zzg = str;
    }

    static /* synthetic */ void zzk(zzsr zzsr, String str) {
        str.getClass();
        zzsr.zze |= 1;
        zzsr.zzf = str;
    }

    static /* synthetic */ void zzl(zzsr zzsr, zzsk zzsk) {
        zzsk.getClass();
        zzsr.zzm = zzsk;
        zzsr.zze |= 128;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007ለ\u0006\bဉ\u0007", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
        } else if (i2 == 3) {
            return new zzsr();
        } else {
            if (i2 == 4) {
                return new zzsq((zzsw) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsr.class) {
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
