package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzrx extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzrx zzb;
    private static volatile zzos zzd;
    private int zze;
    private zzrv zzf;
    private zzrv zzg;

    static {
        zzrx zzrx = new zzrx();
        zzb = zzrx;
        zznf.zzI(zzrx.class, zzrx);
    }

    private zzrx() {
    }

    public static zzrx zzj(byte[] bArr) throws zznp {
        return (zzrx) zznf.zzx(zzb, bArr);
    }

    public final zzrv zzf() {
        zzrv zzrv = this.zzf;
        return zzrv == null ? zzrv.zzg() : zzrv;
    }

    public final zzrv zzg() {
        zzrv zzrv = this.zzg;
        return zzrv == null ? zzrv.zzg() : zzrv;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzrx();
        } else {
            if (i2 == 4) {
                return new zzrw((zzry) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzrx.class) {
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
