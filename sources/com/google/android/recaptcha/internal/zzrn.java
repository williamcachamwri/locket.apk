package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzrn extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzrn zzb;
    private static volatile zzos zzd;
    private int zze;
    private zzmn zzf;
    private zzpl zzg;
    private zzmn zzh;
    private zzpl zzi;

    static {
        zzrn zzrn = new zzrn();
        zzb = zzrn;
        zznf.zzI(zzrn.class, zzrn);
    }

    private zzrn() {
    }

    public static zzrl zzf() {
        return (zzrl) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzrn zzrn, zzpl zzpl) {
        zzpl.getClass();
        zzrn.zzi = zzpl;
        zzrn.zze |= 8;
    }

    static /* synthetic */ void zzj(zzrn zzrn, zzmn zzmn) {
        zzmn.getClass();
        zzrn.zzh = zzmn;
        zzrn.zze |= 4;
    }

    static /* synthetic */ void zzk(zzrn zzrn, zzpl zzpl) {
        zzpl.getClass();
        zzrn.zzg = zzpl;
        zzrn.zze |= 2;
    }

    static /* synthetic */ void zzl(zzrn zzrn, zzmn zzmn) {
        zzmn.getClass();
        zzrn.zzf = zzmn;
        zzrn.zze |= 1;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        } else if (i2 == 3) {
            return new zzrn();
        } else {
            if (i2 == 4) {
                return new zzrl((zzrm) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzrn.class) {
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
