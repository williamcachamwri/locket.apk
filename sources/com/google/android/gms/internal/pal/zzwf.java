package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwf extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwf zzb;
    private String zze = "";
    /* access modifiers changed from: private */
    public int zzf;
    /* access modifiers changed from: private */
    public int zzg;
    /* access modifiers changed from: private */
    public int zzh;

    static {
        zzwf zzwf = new zzwf();
        zzb = zzwf;
        zzacz.zzaF(zzwf.class, zzwf);
    }

    private zzwf() {
    }

    public static zzwe zza() {
        return (zzwe) zzb.zzau();
    }

    static /* synthetic */ void zzd(zzwf zzwf, String str) {
        str.getClass();
        zzwf.zze = str;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
        } else if (i2 == 3) {
            return new zzwf();
        } else {
            if (i2 == 4) {
                return new zzwe((zzwc) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
