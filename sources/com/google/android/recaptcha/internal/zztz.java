package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zztz extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zztz zzb;
    private static volatile zzos zzd;
    private int zze = 0;
    private Object zzf;

    static {
        zztz zztz = new zztz();
        zzb = zztz;
        zznf.zzI(zztz.class, zztz);
    }

    private zztz() {
    }

    static /* synthetic */ void zzM(zztz zztz, zzrt zzrt) {
        zzrt.getClass();
        zztz.zzf = zzrt;
        zztz.zze = 2;
    }

    public static zzty zzi() {
        return (zzty) zzb.zzq();
    }

    public static zztz zzk(byte[] bArr) throws zznp {
        return (zztz) zznf.zzx(zzb, bArr);
    }

    static /* synthetic */ void zzl(zztz zztz, zzre zzre) {
        zzre.getClass();
        zztz.zzf = zzre;
        zztz.zze = 1;
    }

    public final int zzN() {
        int i = this.zze;
        if (i == 0) {
            return 3;
        }
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                return 0;
            }
        }
        return i2;
    }

    public final zzre zzf() {
        if (this.zze == 1) {
            return (zzre) this.zzf;
        }
        return zzre.zzk();
    }

    public final zzrt zzg() {
        if (this.zze == 2) {
            return (zzrt) this.zzf;
        }
        return zzrt.zzg();
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"zzf", "zze", zzre.class, zzrt.class});
        } else if (i2 == 3) {
            return new zztz();
        } else {
            if (i2 == 4) {
                return new zzty((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zztz.class) {
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
