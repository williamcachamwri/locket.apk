package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzu extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzu zzb;
    private int zze;
    private int zzf;

    static {
        zzu zzu = new zzu();
        zzb = zzu;
        zzacz.zzaF(zzu.class, zzu);
    }

    private zzu() {
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဌ\u0000", new Object[]{"zze", "zzf", zzw.zza});
        } else if (i2 == 3) {
            return new zzu();
        } else {
            if (i2 == 4) {
                return new zzt((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
