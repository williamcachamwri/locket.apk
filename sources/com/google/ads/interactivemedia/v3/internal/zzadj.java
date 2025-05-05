package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzadj {
    private final Object zza;
    private final int zzb;

    zzadj(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzadj)) {
            return false;
        }
        zzadj zzadj = (zzadj) obj;
        if (this.zza == zzadj.zza && this.zzb == zzadj.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
