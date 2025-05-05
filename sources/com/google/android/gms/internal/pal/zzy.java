package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzy extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzy zzb;
    private int zze;
    private int zzf;
    private long zzg = -1;

    static {
        zzy zzy = new zzy();
        zzb = zzy;
        zzacz.zzaF(zzy.class, zzy);
    }

    private zzy() {
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", zzv.zza, "zzg"});
        } else if (i2 == 3) {
            return new zzy();
        } else {
            if (i2 == 4) {
                return new zzx((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
