package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzaa extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzaa zzb;
    private int zze;
    private long zzf = -1;
    private int zzg = 1000;

    static {
        zzaa zzaa = new zzaa();
        zzb = zzaa;
        zzacz.zzaF(zzaa.class, zzaa);
    }

    private zzaa() {
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဌ\u0001", new Object[]{"zze", "zzf", "zzg", zzan.zza});
        } else if (i2 == 3) {
            return new zzaa();
        } else {
            if (i2 == 4) {
                return new zzz((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
