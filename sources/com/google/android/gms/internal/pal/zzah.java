package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzah extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzah zzb;
    private int zze;
    private long zzf;
    private int zzg;
    private boolean zzh;
    private zzade zzi = zzay();
    private long zzj;

    static {
        zzah zzah = new zzah();
        zzb = zzah;
        zzacz.zzaF(zzah.class, zzah);
    }

    private zzah() {
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဂ\u0000\u0002င\u0001\u0003ဇ\u0002\u0004\u0016\u0005ဃ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        } else if (i2 == 3) {
            return new zzah();
        } else {
            if (i2 == 4) {
                return new zzag((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
