package com.google.ads.interactivemedia.v3.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzpv extends zzqf {
    static final zzpv zza = new zzpv();

    private zzpv() {
    }

    public final boolean equals(@CheckForNull Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    public final zzqf zza(zzpz zzpz) {
        return zza;
    }

    public final Object zzb() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public final Object zzc(Object obj) {
        return obj;
    }

    @CheckForNull
    public final Object zzd() {
        return null;
    }

    public final boolean zze() {
        return false;
    }
}
