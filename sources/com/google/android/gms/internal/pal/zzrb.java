package com.google.android.gms.internal.pal;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzrb {
    public static final zzrb zza = new zzqz().zza();
    private final Map zzb;

    /* synthetic */ zzrb(Map map, zzra zzra) {
        this.zzb = map;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzrb)) {
            return false;
        }
        return this.zzb.equals(((zzrb) obj).zzb);
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    public final String toString() {
        return this.zzb.toString();
    }

    public final Map zza() {
        return this.zzb;
    }
}
