package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzpe extends Exception {
    private final int zza;

    public zzpe(int i, String str) {
        super(str);
        this.zza = i;
    }

    public final int zza() {
        return this.zza;
    }

    public zzpe(int i, Throwable th) {
        super(th);
        this.zza = i;
    }
}
