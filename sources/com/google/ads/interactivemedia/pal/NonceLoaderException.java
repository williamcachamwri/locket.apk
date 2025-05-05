package com.google.ads.interactivemedia.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class NonceLoaderException extends Exception {
    private final int zza;

    public NonceLoaderException(int i, Exception exc) {
        super("NonceLoader exception, errorCode : " + i, exc);
        this.zza = i;
    }

    public static NonceLoaderException zzb(int i) {
        return new NonceLoaderException(i, new Exception());
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zza;
    }
}
