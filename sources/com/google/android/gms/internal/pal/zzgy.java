package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzgy extends Exception {
    private final int zza;

    public zzgy(int i) {
        super("Signal SDK error code: " + i);
        this.zza = i;
    }

    public final int zza() {
        return this.zza;
    }
}
