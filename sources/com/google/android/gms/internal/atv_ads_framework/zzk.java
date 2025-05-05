package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzk extends zzdh implements zzep {
    /* access modifiers changed from: private */
    public static final zzk zzb;
    private int zzd;
    private String zze = "";
    private boolean zzf;

    static {
        zzk zzk = new zzk();
        zzb = zzk;
        zzdh.zzx(zzk.class, zzk);
    }

    private zzk() {
    }

    public static zzj zza() {
        return (zzj) zzb.zzo();
    }

    static /* synthetic */ void zzc(zzk zzk, String str) {
        str.getClass();
        zzk.zzd |= 1;
        zzk.zze = str;
    }

    static /* synthetic */ void zzd(zzk zzk, boolean z) {
        zzk.zzd |= 2;
        zzk.zzf = z;
    }

    /* access modifiers changed from: protected */
    public final Object zze(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzu(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzk();
        } else {
            if (i2 == 4) {
                return new zzj((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
