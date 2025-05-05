package com.google.ads.interactivemedia.omid.library.adsession;

import com.google.ads.interactivemedia.v3.internal.zzdp;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzj {
    private final String zza = "Google1";
    private final String zzb = "3.35.1";

    private zzj(String str, String str2) {
    }

    public static zzj zza(String str, String str2) {
        zzdp.zza("Google1", "Name is null or empty");
        zzdp.zza("3.35.1", "Version is null or empty");
        return new zzj("Google1", "3.35.1");
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }
}
