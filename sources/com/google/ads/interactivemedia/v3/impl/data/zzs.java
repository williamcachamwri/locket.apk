package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzs extends zzbf {
    private final double end;
    private final boolean played;
    private final double start;

    zzs(double d, double d2, boolean z) {
        this.start = d;
        this.end = d2;
        this.played = z;
    }

    public double end() {
        return this.end;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbf) {
            zzbf zzbf = (zzbf) obj;
            return Double.doubleToLongBits(this.start) == Double.doubleToLongBits(zzbf.start()) && Double.doubleToLongBits(this.end) == Double.doubleToLongBits(zzbf.end()) && this.played == zzbf.played();
        }
    }

    public int hashCode() {
        return ((((((int) ((Double.doubleToLongBits(this.start) >>> 32) ^ Double.doubleToLongBits(this.start))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.end) >>> 32) ^ Double.doubleToLongBits(this.end)))) * 1000003) ^ (true != this.played ? 1237 : 1231);
    }

    public boolean played() {
        return this.played;
    }

    public double start() {
        return this.start;
    }

    public String toString() {
        return "CuePointData{start=" + this.start + ", end=" + this.end + ", played=" + this.played + "}";
    }
}
