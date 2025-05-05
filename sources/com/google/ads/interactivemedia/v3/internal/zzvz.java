package com.google.ads.interactivemedia.v3.internal;

import java.util.Set;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzvz extends zzvw {
    private final zzxy zza = new zzxy(false);

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzvz) {
            return ((zzvz) obj).zza.equals(this.zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Set zza() {
        return this.zza.entrySet();
    }

    public final void zzb(String str, zzvw zzvw) {
        this.zza.put(str, zzvw);
    }
}
