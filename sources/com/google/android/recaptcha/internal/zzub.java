package com.google.android.recaptcha.internal;

import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzub extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzub zzb;
    private static volatile zzos zzd;
    private zznm zze = zzB();

    static {
        zzub zzub = new zzub();
        zzb = zzub;
        zznf.zzI(zzub.class, zzub);
    }

    private zzub() {
    }

    public static zzub zzg(byte[] bArr) throws zznp {
        return (zzub) zznf.zzx(zzb, bArr);
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
            return zzF(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzuh.class});
        } else if (i2 == 3) {
            return new zzub();
        } else {
            if (i2 == 4) {
                return new zzua((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzub.class) {
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
