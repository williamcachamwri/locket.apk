package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzp extends zzdh implements zzep {
    /* access modifiers changed from: private */
    public static final zzp zzb;
    private int zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzp zzp = new zzp();
        zzb = zzp;
        zzdh.zzx(zzp.class, zzp);
    }

    private zzp() {
    }

    public static zzo zza() {
        return (zzo) zzb.zzo();
    }

    static /* synthetic */ void zzc(zzp zzp, String str) {
        str.getClass();
        zzp.zzd |= 2;
        zzp.zzf = str;
    }

    static /* synthetic */ void zzd(zzp zzp, String str) {
        str.getClass();
        zzp.zzd |= 4;
        zzp.zzg = str;
    }

    static /* synthetic */ void zzf(zzp zzp, int i) {
        zzp.zze = i - 1;
        zzp.zzd |= 1;
    }

    /* access modifiers changed from: protected */
    public final Object zze(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzu(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zzd", "zze", zzq.zza, "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zzp();
        } else {
            if (i2 == 4) {
                return new zzo((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
