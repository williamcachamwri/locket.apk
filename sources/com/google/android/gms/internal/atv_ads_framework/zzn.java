package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzn extends zzdh implements zzep {
    /* access modifiers changed from: private */
    public static final zzn zzb;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzn zzn = new zzn();
        zzb = zzn;
        zzdh.zzx(zzn.class, zzn);
    }

    private zzn() {
    }

    public static zzm zza() {
        return (zzm) zzb.zzo();
    }

    static /* synthetic */ void zzc(zzn zzn, int i) {
        zzn.zze = i - 1;
        zzn.zzd |= 1;
    }

    static /* synthetic */ void zzd(zzn zzn, int i) {
        zzn.zzf = i - 1;
        zzn.zzd |= 2;
    }

    static /* synthetic */ void zzf(zzn zzn, int i) {
        zzn.zzg = i - 1;
        zzn.zzd |= 4;
    }

    /* access modifiers changed from: protected */
    public final Object zze(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzu(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဌ\u0002", new Object[]{"zzd", "zze", zzi.zza, "zzf", zzh.zza, "zzg", zzl.zza});
        } else if (i2 == 3) {
            return new zzn();
        } else {
            if (i2 == 4) {
                return new zzm((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
