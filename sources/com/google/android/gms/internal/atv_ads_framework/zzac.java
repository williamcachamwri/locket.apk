package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzac extends zzdh implements zzep {
    /* access modifiers changed from: private */
    public static final zzac zzb;
    private int zzd;
    private int zze = 0;
    private Object zzf;
    private zzu zzg;
    private zzk zzh;
    private zzs zzi;
    private zzp zzj;

    static {
        zzac zzac = new zzac();
        zzb = zzac;
        zzdh.zzx(zzac.class, zzac);
    }

    private zzac() {
    }

    public static zzab zza() {
        return (zzab) zzb.zzo();
    }

    static /* synthetic */ void zzc(zzac zzac, zzp zzp) {
        zzp.getClass();
        zzac.zzj = zzp;
        zzac.zzd |= 8;
    }

    static /* synthetic */ void zzd(zzac zzac, zzaa zzaa) {
        zzaa.getClass();
        zzac.zzf = zzaa;
        zzac.zze = 5;
    }

    static /* synthetic */ void zzf(zzac zzac, zzn zzn) {
        zzn.getClass();
        zzac.zzf = zzn;
        zzac.zze = 6;
    }

    static /* synthetic */ void zzg(zzac zzac, zzu zzu) {
        zzu.getClass();
        zzac.zzg = zzu;
        zzac.zzd |= 1;
    }

    static /* synthetic */ void zzh(zzac zzac, zzk zzk) {
        zzk.getClass();
        zzac.zzh = zzk;
        zzac.zzd |= 2;
    }

    static /* synthetic */ void zzi(zzac zzac, zzs zzs) {
        zzs.getClass();
        zzac.zzi = zzs;
        zzac.zzd |= 4;
    }

    /* access modifiers changed from: protected */
    public final Object zze(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzu(zzb, "\u0001\u0006\u0001\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003\u0005<\u0000\u0006<\u0000", new Object[]{"zzf", "zze", "zzd", "zzg", "zzh", "zzi", "zzj", zzaa.class, zzn.class});
        } else if (i2 == 3) {
            return new zzac();
        } else {
            if (i2 == 4) {
                return new zzab((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
