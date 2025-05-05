package com.google.android.recaptcha.internal;

import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzse extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzse zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private String zzk = "";
    private zznl zzl = zzA();
    private zzlg zzm = zzlg.zzb;
    private String zzn = "";
    private zzsg zzo;
    private zzsc zzp;

    static {
        zzse zzse = new zzse();
        zzb = zzse;
        zznf.zzI(zzse.class, zzse);
    }

    private zzse() {
    }

    public static zzse zzj() {
        return zzb;
    }

    public final String zzM() {
        return this.zzi;
    }

    public final String zzN() {
        return this.zzh;
    }

    public final String zzO() {
        return this.zzj;
    }

    public final String zzP() {
        return this.zzk;
    }

    public final List zzQ() {
        return this.zzl;
    }

    public final boolean zzR() {
        return (this.zze & 512) != 0;
    }

    public final boolean zzS() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzT() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzU() {
        return (this.zze & 256) != 0;
    }

    public final boolean zzV() {
        return (this.zze & 64) != 0;
    }

    public final zzlg zzf() {
        return this.zzm;
    }

    public final zzsc zzg() {
        zzsc zzsc = this.zzp;
        return zzsc == null ? zzsc.zzi() : zzsc;
    }

    public final zzsg zzk() {
        zzsg zzsg = this.zzo;
        return zzsg == null ? zzsg.zzk() : zzsg;
    }

    public final String zzl() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u000b\u0000\u0001\u0001\f\u000b\u0000\u0001\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ለ\u0005\u0007%\bည\u0006\tለ\u0007\u000bဉ\b\fဉ\t", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
        } else if (i2 == 3) {
            return new zzse();
        } else {
            if (i2 == 4) {
                return new zzsd((zzsp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzse.class) {
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
