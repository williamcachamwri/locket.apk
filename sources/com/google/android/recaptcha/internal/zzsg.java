package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsg extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsg zzb;
    private static volatile zzos zzd;
    private zzlg zze = zzlg.zzb;
    private String zzf = "";
    private long zzg;
    private zzlg zzh = zzlg.zzb;
    private String zzi = "";
    private String zzj = "";

    static {
        zzsg zzsg = new zzsg();
        zzb = zzsg;
        zznf.zzI(zzsg.class, zzsg);
    }

    private zzsg() {
    }

    public static zzsg zzk() {
        return zzb;
    }

    public final long zzf() {
        return this.zzg;
    }

    public final zzlg zzg() {
        return this.zzh;
    }

    public final zzlg zzi() {
        return this.zze;
    }

    public final String zzl() {
        return this.zzf;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001\n\u0002Ȉ\u0003\u0002\u0004\n\u0005Ȉ\u0006Ȉ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        } else if (i2 == 3) {
            return new zzsg();
        } else {
            if (i2 == 4) {
                return new zzsf((zzsp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsg.class) {
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
