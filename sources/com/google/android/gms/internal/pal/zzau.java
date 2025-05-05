package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzau extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzau zzb;
    private int zze;
    private zzadf zzf = zzaz();
    private zzaby zzg = zzaby.zzb;
    private int zzh = 1;
    private int zzi = 1;

    static {
        zzau zzau = new zzau();
        zzb = zzau;
        zzacz.zzaF(zzau.class, zzau);
    }

    private zzau() {
    }

    public static zzat zza() {
        return (zzat) zzb.zzau();
    }

    static /* synthetic */ void zzd(zzau zzau, zzaby zzaby) {
        zzadf zzadf = zzau.zzf;
        if (!zzadf.zzc()) {
            zzau.zzf = zzacz.zzaA(zzadf);
        }
        zzau.zzf.add(zzaby);
    }

    static /* synthetic */ void zze(zzau zzau, zzaby zzaby) {
        zzau.zze |= 1;
        zzau.zzg = zzaby;
    }

    static /* synthetic */ void zzf(zzau zzau, int i) {
        zzau.zzi = 2;
        zzau.zze |= 4;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001c\u0002ည\u0000\u0003ဌ\u0001\u0004ဌ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh", zzao.zza, "zzi", zzam.zza});
        } else if (i2 == 3) {
            return new zzau();
        } else {
            if (i2 == 4) {
                return new zzat((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
