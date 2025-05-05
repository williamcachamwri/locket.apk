package com.google.ads.interactivemedia.v3.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzqi extends zzqf {
    private final Object zza;

    zzqi(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzqi) {
            return this.zza.equals(((zzqi) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "Optional.of(" + obj + ")";
    }

    public final zzqf zza(zzpz zzpz) {
        return new zzqi(zzpz.zza(this.zza));
    }

    public final Object zzb() {
        return this.zza;
    }

    public final Object zzc(Object obj) {
        return this.zza;
    }

    public final Object zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return true;
    }
}
