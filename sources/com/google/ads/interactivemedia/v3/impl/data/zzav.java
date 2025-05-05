package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzav extends zzcj {
    private final long currentTime;
    private final long duration;
    private final String timeUnit;

    zzav(long j, long j2, String str) {
        this.currentTime = j;
        this.duration = j2;
        if (str != null) {
            this.timeUnit = str;
            return;
        }
        throw new NullPointerException("Null timeUnit");
    }

    public long currentTime() {
        return this.currentTime;
    }

    public long duration() {
        return this.duration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcj) {
            zzcj zzcj = (zzcj) obj;
            return this.currentTime == zzcj.currentTime() && this.duration == zzcj.duration() && this.timeUnit.equals(zzcj.timeUnit());
        }
    }

    public int hashCode() {
        long j = this.duration;
        long j2 = this.currentTime;
        return ((((int) (j ^ (j >>> 32))) ^ ((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003)) * 1000003) ^ this.timeUnit.hashCode();
    }

    public String timeUnit() {
        return this.timeUnit;
    }

    public String toString() {
        return "TimeUpdateData{currentTime=" + this.currentTime + ", duration=" + this.duration + ", timeUnit=" + this.timeUnit + "}";
    }
}
