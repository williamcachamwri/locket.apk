package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaht {
    private final Object zza;
    private final int zzb;

    zzaht(Object obj) {
        this.zzb = System.identityHashCode(obj);
        this.zza = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzaht)) {
            return false;
        }
        zzaht zzaht = (zzaht) obj;
        if (this.zzb == zzaht.zzb && this.zza == zzaht.zza) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb;
    }
}
