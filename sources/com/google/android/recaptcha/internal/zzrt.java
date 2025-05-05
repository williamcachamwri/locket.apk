package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzrt extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzrt zzb;
    private static volatile zzos zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private String zzh = "";
    private String zzi = "";
    private zzmn zzj;
    private zzpl zzk;
    private int zzl;
    private zzrb zzm;
    private zznm zzn = zzB();

    static {
        zzrt zzrt = new zzrt();
        zzb = zzrt;
        zznf.zzI(zzrt.class, zzrt);
    }

    private zzrt() {
    }

    public static zzrt zzg() {
        return zzb;
    }

    public static zzrt zzi(byte[] bArr) throws zznp {
        return (zzrt) zznf.zzx(zzb, bArr);
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\t\u0000\u0001\u0001\u000b\t\u0000\u0001\u0000\u0001\u0004\u0003ဉ\u0000\u0004ဉ\u0001\u0005\f\u0007\u001b\b\f\tȈ\nȈ\u000bဉ\u0002", new Object[]{"zze", "zzf", "zzj", "zzk", "zzl", "zzn", zzrk.class, "zzg", "zzh", "zzi", "zzm"});
        } else if (i2 == 3) {
            return new zzrt();
        } else {
            if (i2 == 4) {
                return new zzrr((zzrs) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzrt.class) {
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
