package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.internal.zzaho;
import com.google.ads.interactivemedia.v3.internal.zzahr;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzd implements AdPodInfo {
    public int adPosition = 1;
    public List<Long> adsDurationsMs = new ArrayList();
    public boolean isBumper = false;
    public double maxDuration = -1.0d;
    public int podIndex;
    public double timeOffset;
    public int totalAds = 1;

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return zzaho.zzf(this, obj, false, (Class) null, false, new String[0]);
    }

    public int getAdPosition() {
        return this.adPosition;
    }

    public List<Long> getAdsDurationsMs() {
        return this.adsDurationsMs;
    }

    public double getMaxDuration() {
        return this.maxDuration;
    }

    public int getPodIndex() {
        return this.podIndex;
    }

    public double getTimeOffset() {
        return this.timeOffset;
    }

    public int getTotalAds() {
        return this.totalAds;
    }

    public int hashCode() {
        return zzahr.zza(this, new String[0]);
    }

    public boolean isBumper() {
        return this.isBumper;
    }

    public String toString() {
        int i = this.totalAds;
        int i2 = this.adPosition;
        boolean z = this.isBumper;
        double d = this.maxDuration;
        String valueOf = String.valueOf(this.adsDurationsMs);
        int i3 = this.podIndex;
        double d2 = this.timeOffset;
        return "AdPodInfo [totalAds=" + i + ", adPosition=" + i2 + ", isBumper=" + z + ", maxDuration=" + d + ", adsDurationsMs=" + valueOf + ", podIndex=" + i3 + ", timeOffset=" + d2 + "]";
    }
}
