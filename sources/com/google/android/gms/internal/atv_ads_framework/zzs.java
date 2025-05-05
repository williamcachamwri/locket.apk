package com.google.android.gms.internal.atv_ads_framework;

import com.amplitude.analytics.BuildConfig;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzs extends zzdh implements zzep {
    /* access modifiers changed from: private */
    public static final zzs zzb;
    private int zzd;
    private String zze = "";

    static {
        zzs zzs = new zzs();
        zzb = zzs;
        zzdh.zzx(zzs.class, zzs);
    }

    private zzs() {
    }

    public static zzr zza() {
        return (zzr) zzb.zzo();
    }

    static /* synthetic */ void zzc(zzs zzs, String str) {
        zzs.zzd |= 1;
        zzs.zze = BuildConfig.VERSION_NAME;
    }

    /* access modifiers changed from: protected */
    public final Object zze(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzu(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zzd", "zze"});
        } else if (i2 == 3) {
            return new zzs();
        } else {
            if (i2 == 4) {
                return new zzr((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
