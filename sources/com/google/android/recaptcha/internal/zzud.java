package com.google.android.recaptcha.internal;

import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzud extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzud zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private zznk zzg = zzy();
    private zznl zzh = zzA();
    private zztn zzi;

    static {
        zzud zzud = new zzud();
        zzb = zzud;
        zznf.zzI(zzud.class, zzud);
    }

    private zzud() {
    }

    public static zzud zzi(byte[] bArr) throws zznp {
        return (zzud) zznf.zzx(zzb, bArr);
    }

    public final zztn zzf() {
        zztn zztn = this.zzi;
        return zztn == null ? zztn.zzg() : zztn;
    }

    public final String zzj() {
        return this.zzf;
    }

    public final List zzk() {
        return this.zzh;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001Ȉ\u0002'\u0003%\u0004ဉ\u0000", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (i2 == 3) {
            return new zzud();
        } else {
            if (i2 == 4) {
                return new zzuc((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzud.class) {
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
