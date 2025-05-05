package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzrq extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzrq zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private zzqv zzg;
    private zzqm zzh;
    private zzqy zzi;
    private String zzj = "";
    private String zzk = "";

    static {
        zzrq zzrq = new zzrq();
        zzb = zzrq;
        zznf.zzI(zzrq.class, zzrq);
    }

    private zzrq() {
    }

    public static zzro zzf() {
        return (zzro) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzrq zzrq, zzqm zzqm) {
        zzqm.getClass();
        zzrq.zzh = zzqm;
        zzrq.zze |= 2;
    }

    static /* synthetic */ void zzj(zzrq zzrq, String str) {
        str.getClass();
        zzrq.zzk = str;
    }

    static /* synthetic */ void zzk(zzrq zzrq, String str) {
        str.getClass();
        zzrq.zzj = str;
    }

    static /* synthetic */ void zzl(zzrq zzrq, String str) {
        str.getClass();
        zzrq.zzf = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002ဉ\u0000\u0003ဉ\u0001\u0004ဉ\u0002\u0005Ȉ\u0006Ȉ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i2 == 3) {
            return new zzrq();
        } else {
            if (i2 == 4) {
                return new zzro((zzrp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzrq.class) {
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
