package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzh implements AdProgressInfo {
    private final double zza;
    private final double zzb;
    private final int zzc;
    private final int zzd;
    private final double zze;
    private final double zzf;

    zzh(double d, double d2, int i, int i2, double d3, double d4, List list) {
        this.zza = d;
        this.zzb = d2;
        this.zzc = i;
        this.zzd = i2;
        this.zze = d3;
        this.zzf = d4;
    }

    public final double getAdBreakDuration() {
        return this.zze;
    }

    public final double getAdPeriodDuration() {
        return this.zzf;
    }

    public final int getAdPosition() {
        return this.zzc;
    }

    public final double getCurrentTime() {
        return this.zza;
    }

    public final double getDuration() {
        return this.zzb;
    }

    public final int getTotalAds() {
        return this.zzd;
    }
}
