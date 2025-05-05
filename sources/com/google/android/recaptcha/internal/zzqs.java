package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzqs extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzqs zzb;
    private static volatile zzos zzd;
    private String zze = "";
    private String zzf = "";
    /* access modifiers changed from: private */
    public int zzg;
    private String zzh = "";
    /* access modifiers changed from: private */
    public String zzi = "";
    private int zzj;
    /* access modifiers changed from: private */
    public int zzk;

    static {
        zzqs zzqs = new zzqs();
        zzb = zzqs;
        zznf.zzI(zzqs.class, zzqs);
    }

    private zzqs() {
    }

    static /* synthetic */ void zzO(zzqs zzqs, String str) {
        str.getClass();
        zzqs.zze = str;
    }

    public static zzqq zzg() {
        return (zzqq) zzb.zzq();
    }

    public static zzqs zzj() {
        return zzb;
    }

    public final int zzf() {
        return this.zzg;
    }

    public final String zzk() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0000\u0000\u0001Ȉ\u0002\u0004\u0003Ȉ\u0004\u0004\u0005Ȉ\u0006Ȉ\u0007\u0004", new Object[]{"zze", "zzg", "zzi", "zzj", "zzf", "zzh", "zzk"});
        } else if (i2 == 3) {
            return new zzqs();
        } else {
            if (i2 == 4) {
                return new zzqq((zzqr) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzqs.class) {
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
