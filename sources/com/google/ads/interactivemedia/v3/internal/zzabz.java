package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzabz {
    public static final boolean zza;
    public static final zzwk zzb;
    public static final zzwk zzc;
    public static final zzwk zzd;

    static {
        boolean z;
        zzwk zzwk;
        try {
            Class.forName("java.sql.Date");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        zza = z;
        if (z) {
            zzb = zzabs.zza;
            zzc = zzabv.zza;
            zzwk = zzaby.zza;
        } else {
            zzwk = null;
            zzb = null;
            zzc = null;
        }
        zzd = zzwk;
    }
}
