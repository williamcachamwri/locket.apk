package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaz extends zzcp {
    private final float volume;

    private zzaz(float f) {
        this.volume = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzcp) && Float.floatToIntBits(this.volume) == Float.floatToIntBits(((zzcp) obj).volume());
    }

    public int hashCode() {
        return Float.floatToIntBits(this.volume) ^ 1000003;
    }

    public String toString() {
        return "VolumeUpdateData{volume=" + this.volume + "}";
    }

    public float volume() {
        return this.volume;
    }
}
