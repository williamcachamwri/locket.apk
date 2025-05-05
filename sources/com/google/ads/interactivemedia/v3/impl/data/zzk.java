package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzk extends zzb {
    private final String appState;
    private final String eventId;
    private final long nativeTime;
    private final zzbb nativeViewBounds;
    private final boolean nativeViewHidden;
    private final zzbb nativeViewVisibleBounds;
    private final double nativeVolume;
    private final String queryId;

    private zzk(String str, String str2, String str3, long j, double d, boolean z, zzbb zzbb, zzbb zzbb2) {
        this.queryId = str;
        this.eventId = str2;
        this.appState = str3;
        this.nativeTime = j;
        this.nativeVolume = d;
        this.nativeViewHidden = z;
        this.nativeViewBounds = zzbb;
        this.nativeViewVisibleBounds = zzbb2;
    }

    public String appState() {
        return this.appState;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzb) {
            zzb zzb = (zzb) obj;
            return this.queryId.equals(zzb.queryId()) && this.eventId.equals(zzb.eventId()) && this.appState.equals(zzb.appState()) && this.nativeTime == zzb.nativeTime() && Double.doubleToLongBits(this.nativeVolume) == Double.doubleToLongBits(zzb.nativeVolume()) && this.nativeViewHidden == zzb.nativeViewHidden() && this.nativeViewBounds.equals(zzb.nativeViewBounds()) && this.nativeViewVisibleBounds.equals(zzb.nativeViewVisibleBounds());
        }
    }

    public String eventId() {
        return this.eventId;
    }

    public int hashCode() {
        int hashCode = ((((this.queryId.hashCode() ^ 1000003) * 1000003) ^ this.eventId.hashCode()) * 1000003) ^ this.appState.hashCode();
        long doubleToLongBits = (Double.doubleToLongBits(this.nativeVolume) >>> 32) ^ Double.doubleToLongBits(this.nativeVolume);
        int i = true != this.nativeViewHidden ? 1237 : 1231;
        long j = this.nativeTime;
        return (((((((((hashCode * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ ((int) doubleToLongBits)) * 1000003) ^ i) * 1000003) ^ this.nativeViewBounds.hashCode()) * 1000003) ^ this.nativeViewVisibleBounds.hashCode();
    }

    public long nativeTime() {
        return this.nativeTime;
    }

    public zzbb nativeViewBounds() {
        return this.nativeViewBounds;
    }

    public boolean nativeViewHidden() {
        return this.nativeViewHidden;
    }

    public zzbb nativeViewVisibleBounds() {
        return this.nativeViewVisibleBounds;
    }

    public double nativeVolume() {
        return this.nativeVolume;
    }

    public String queryId() {
        return this.queryId;
    }

    public String toString() {
        zzbb zzbb = this.nativeViewVisibleBounds;
        String valueOf = String.valueOf(this.nativeViewBounds);
        String valueOf2 = String.valueOf(zzbb);
        return "ActivityMonitorData{queryId=" + this.queryId + ", eventId=" + this.eventId + ", appState=" + this.appState + ", nativeTime=" + this.nativeTime + ", nativeVolume=" + this.nativeVolume + ", nativeViewHidden=" + this.nativeViewHidden + ", nativeViewBounds=" + valueOf + ", nativeViewVisibleBounds=" + valueOf2 + "}";
    }
}
