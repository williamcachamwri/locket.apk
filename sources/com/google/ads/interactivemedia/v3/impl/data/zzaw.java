package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaw extends zzcn {
    private final Integer downloadBandwidthKbps;
    private final boolean rendersUiNatively;

    zzaw(Integer num, boolean z) {
        this.downloadBandwidthKbps = num;
        this.rendersUiNatively = z;
    }

    public Integer downloadBandwidthKbps() {
        return this.downloadBandwidthKbps;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcn) {
            zzcn zzcn = (zzcn) obj;
            Integer num = this.downloadBandwidthKbps;
            if (num != null ? num.equals(zzcn.downloadBandwidthKbps()) : zzcn.downloadBandwidthKbps() == null) {
                if (this.rendersUiNatively == zzcn.rendersUiNatively()) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        Integer num = this.downloadBandwidthKbps;
        return (((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003) ^ (true != this.rendersUiNatively ? 1237 : 1231);
    }

    public boolean rendersUiNatively() {
        return this.rendersUiNatively;
    }

    public String toString() {
        return "VideoEnvironmentData{downloadBandwidthKbps=" + this.downloadBandwidthKbps + ", rendersUiNatively=" + this.rendersUiNatively + "}";
    }
}
