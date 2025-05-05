package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zztq extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zztq zzb;
    private static volatile zzos zzd;
    private String zze = "";
    private String zzf = "";
    /* access modifiers changed from: private */
    public String zzg = "";
    /* access modifiers changed from: private */
    public int zzh;
    private String zzi = "";
    private String zzj = "";
    /* access modifiers changed from: private */
    public String zzk = "";

    static {
        zztq zztq = new zztq();
        zzb = zztq;
        zznf.zzI(zztq.class, zztq);
    }

    private zztq() {
    }

    static /* synthetic */ void zzM(zztq zztq, String str) {
        str.getClass();
        zztq.zzi = str;
    }

    static /* synthetic */ void zzN(zztq zztq, String str) {
        str.getClass();
        zztq.zze = str;
    }

    public static zztp zzf() {
        return (zztp) zzb.zzq();
    }

    static /* synthetic */ void zzj(zztq zztq, String str) {
        str.getClass();
        zztq.zzj = str;
    }

    static /* synthetic */ void zzk(zztq zztq, String str) {
        str.getClass();
        zztq.zzf = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0007\u0000\u0000\u0001\b\u0007\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\f\u0006Ȉ\u0007Ȉ\bȈ", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i2 == 3) {
            return new zztq();
        } else {
            if (i2 == 4) {
                return new zztp((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zztq.class) {
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
