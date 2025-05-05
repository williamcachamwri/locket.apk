package com.google.ads.interactivemedia.v3.internal;

import java.util.NoSuchElementException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzrx extends zzss {
    private final Object zza;
    private boolean zzb;

    zzrx(Object obj) {
        this.zza = obj;
    }

    public final boolean hasNext() {
        return !this.zzb;
    }

    public final Object next() {
        if (!this.zzb) {
            this.zzb = true;
            return this.zza;
        }
        throw new NoSuchElementException();
    }
}
