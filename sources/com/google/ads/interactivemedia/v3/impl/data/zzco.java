package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzco {
    public abstract zzcp build();

    public abstract zzco volume(float f);

    public zzco volumePercentage(int i) {
        return volume(((float) Math.min(Math.max(i, 0), 100)) / 100.0f);
    }
}
