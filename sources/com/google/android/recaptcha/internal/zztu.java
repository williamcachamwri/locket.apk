package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zztu extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zztu zzb;
    private static volatile zzos zzd;
    private int zze;

    static {
        zztu zztu = new zztu();
        zzb = zztu;
        zznf.zzI(zztu.class, zztu);
    }

    private zztu() {
    }

    public static zztu zzg(byte[] bArr) throws zznp {
        return (zztu) zznf.zzx(zzb, bArr);
    }

    public final zztx zzi() {
        zztx zzb2 = zztx.zzb(this.zze);
        return zzb2 == null ? zztx.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"zze"});
        } else if (i2 == 3) {
            return new zztu();
        } else {
            if (i2 == 4) {
                return new zztt((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zztu.class) {
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
