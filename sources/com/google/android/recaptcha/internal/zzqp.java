package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzqp extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzqp zzb;
    private static volatile zzos zzd;
    private zzlg zze = zzlg.zzb;
    private String zzf = "";
    private zzlg zzg;
    private String zzh;
    private String zzi;
    private zzlg zzj;
    private String zzk;
    private zzlg zzl;

    static {
        zzqp zzqp = new zzqp();
        zzb = zzqp;
        zznf.zzI(zzqp.class, zzqp);
    }

    private zzqp() {
        zzlg zzlg = zzlg.zzb;
        this.zzg = zzlg;
        this.zzh = "";
        this.zzi = "";
        this.zzj = zzlg;
        this.zzk = "";
        this.zzl = zzlg;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001\n\u0002Ȉ\u0003\n\u0004Ȉ\u0005Ȉ\u0006\n\u0007Ȉ\b\n", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        } else if (i2 == 3) {
            return new zzqp();
        } else {
            if (i2 == 4) {
                return new zzqn((zzqo) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzqp.class) {
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
