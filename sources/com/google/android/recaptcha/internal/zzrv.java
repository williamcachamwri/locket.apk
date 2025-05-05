package com.google.android.recaptcha.internal;

import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzrv extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzrv zzb;
    private static volatile zzos zzd;
    private zznm zze = zznf.zzB();

    static {
        zzrv zzrv = new zzrv();
        zzb = zzrv;
        zznf.zzI(zzrv.class, zzrv);
    }

    private zzrv() {
    }

    public static zzrv zzg() {
        return zzb;
    }

    public final List zzi() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zzrv();
        } else {
            if (i2 == 4) {
                return new zzru((zzry) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzrv.class) {
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
