package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zztj extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zztj zzb;
    private static volatile zzos zzd;
    private int zze = 0;
    private Object zzf;

    static {
        zztj zztj = new zztj();
        zzb = zztj;
        zznf.zzI(zztj.class, zztj);
    }

    private zztj() {
    }

    static /* synthetic */ void zzM(zztj zztj, float f) {
        zztj.zze = 9;
        zztj.zzf = Float.valueOf(f);
    }

    static /* synthetic */ void zzN(zztj zztj, int i) {
        zztj.zze = 4;
        zztj.zzf = Integer.valueOf(i);
    }

    static /* synthetic */ void zzO(zztj zztj, int i) {
        zztj.zze = 5;
        zztj.zzf = Integer.valueOf(i);
    }

    static /* synthetic */ void zzP(zztj zztj, long j) {
        zztj.zze = 7;
        zztj.zzf = Long.valueOf(j);
    }

    static /* synthetic */ void zzQ(zztj zztj, String str) {
        str.getClass();
        zztj.zze = 11;
        zztj.zzf = str;
    }

    public static zzti zzf() {
        return (zzti) zzb.zzq();
    }

    static /* synthetic */ void zzi(zztj zztj, boolean z) {
        zztj.zze = 1;
        zztj.zzf = Boolean.valueOf(z);
    }

    static /* synthetic */ void zzj(zztj zztj, zzlg zzlg) {
        zztj.zze = 2;
        zztj.zzf = zzlg;
    }

    static /* synthetic */ void zzk(zztj zztj, String str) {
        str.getClass();
        zztj.zze = 3;
        zztj.zzf = str;
    }

    static /* synthetic */ void zzl(zztj zztj, double d) {
        zztj.zze = 10;
        zztj.zzf = Double.valueOf(d);
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u000b\u0001\u0000\u0001\u000b\u000b\u0000\u0000\u0000\u0001:\u0000\u0002=\u0000\u0003Ȼ\u0000\u0004B\u0000\u0005B\u0000\u0006>\u0000\u0007C\u0000\b6\u0000\t4\u0000\n3\u0000\u000bȻ\u0000", new Object[]{"zzf", "zze"});
        } else if (i2 == 3) {
            return new zztj();
        } else {
            if (i2 == 4) {
                return new zzti((zztl) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zztj.class) {
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
