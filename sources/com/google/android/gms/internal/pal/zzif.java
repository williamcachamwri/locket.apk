package com.google.android.gms.internal.pal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzif extends zzil {
    static final zzif zza = new zzif();

    private zzif() {
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

    public final zzil zza(zzii zzii) {
        zzii.getClass();
        return zza;
    }

    public final Object zzb() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public final Object zzc(Object obj) {
        return obj;
    }

    public final boolean zzd() {
        return false;
    }
}
