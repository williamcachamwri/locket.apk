package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzng extends Exception {
    private final int zza;

    public zzng(int i) {
        super("Signal SDK error code: " + i);
        this.zza = i;
    }

    public final int zza() {
        return this.zza;
    }
}
