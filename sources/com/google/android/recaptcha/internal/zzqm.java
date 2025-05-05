package com.google.android.recaptcha.internal;

import java.util.Iterator;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzqm extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzqm zzb;
    private static volatile zzos zzd;
    /* access modifiers changed from: private */
    public int zze;
    private String zzf = "";
    /* access modifiers changed from: private */
    public String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private zznk zzk = zzy();

    static {
        zzqm zzqm = new zzqm();
        zzb = zzqm;
        zznf.zzI(zzqm.class, zzqm);
    }

    private zzqm() {
    }

    static /* synthetic */ void zzM(zzqm zzqm, String str) {
        str.getClass();
        zzqm.zzf = str;
    }

    static /* synthetic */ void zzN(zzqm zzqm, String str) {
        str.getClass();
        zzqm.zzi = str;
    }

    public static zzqj zzf() {
        return (zzqj) zzb.zzq();
    }

    static /* synthetic */ void zzi(zzqm zzqm, Iterable iterable) {
        zznk zznk = zzqm.zzk;
        if (!zznk.zzc()) {
            zzqm.zzk = zznf.zzz(zznk);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzqm.zzk.zzh(((zzqk) it.next()).zza());
        }
    }

    static /* synthetic */ void zzk(zzqm zzqm, String str) {
        str.getClass();
        zzqm.zzj = str;
    }

    static /* synthetic */ void zzl(zzqm zzqm, String str) {
        str.getClass();
        zzqm.zzh = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007,", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        } else if (i2 == 3) {
            return new zzqm();
        } else {
            if (i2 == 4) {
                return new zzqj((zzql) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzqm.class) {
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
