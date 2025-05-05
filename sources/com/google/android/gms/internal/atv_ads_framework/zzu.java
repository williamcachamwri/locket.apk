package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzu extends zzdh implements zzep {
    /* access modifiers changed from: private */
    public static final zzu zzb;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzu zzu = new zzu();
        zzb = zzu;
        zzdh.zzx(zzu.class, zzu);
    }

    private zzu() {
    }

    public static zzt zza() {
        return (zzt) zzb.zzo();
    }

    static /* synthetic */ void zzc(zzu zzu, String str) {
        str.getClass();
        zzu.zzd |= 1;
        zzu.zze = str;
    }

    static /* synthetic */ void zzd(zzu zzu, String str) {
        str.getClass();
        zzu.zzd |= 2;
        zzu.zzf = str;
    }

    /* access modifiers changed from: protected */
    public final Object zze(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzu(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        } else if (i2 == 3) {
            return new zzu();
        } else {
            if (i2 == 4) {
                return new zzt((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }
}
