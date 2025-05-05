package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzax extends zzco {
    private byte set$0;
    private float volume;

    zzax() {
    }

    public zzcp build() {
        if (this.set$0 == 1) {
            return new zzaz(this.volume);
        }
        throw new IllegalStateException("Missing required properties: volume");
    }

    public zzco volume(float f) {
        this.volume = f;
        this.set$0 = (byte) (this.set$0 | 1);
        return this;
    }
}
