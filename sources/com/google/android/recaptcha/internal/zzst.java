package com.google.android.recaptcha.internal;

import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzst extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzst zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private long zzg;
    private int zzh;
    private zznm zzi = zzB();
    private zzlg zzj = zzlg.zzb;
    private String zzk = "";
    private String zzl = "";

    static {
        zzst zzst = new zzst();
        zzb = zzst;
        zznf.zzI(zzst.class, zzst);
    }

    private zzst() {
    }

    public static zzst zzi() {
        return zzb;
    }

    public final zzlg zzf() {
        return this.zzj;
    }

    public final String zzj() {
        return this.zzf;
    }

    public final List zzk() {
        return this.zzi;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0007\u0000\u0001\u0001\b\u0007\u0000\u0001\u0000\u0001ለ\u0000\u0002ဂ\u0001\u0004ဌ\u0002\u0005\u001b\u0006ည\u0003\u0007ለ\u0004\bለ\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzsv.class, "zzj", "zzk", "zzl"});
        } else if (i2 == 3) {
            return new zzst();
        } else {
            if (i2 == 4) {
                return new zzss((zzsw) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzst.class) {
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
