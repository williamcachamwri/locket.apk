package com.google.android.gms.internal.pal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zziq extends zzil {
    private final Object zza;

    zziq(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zziq) {
            return this.zza.equals(((zziq) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        return "Optional.of(" + this.zza + ")";
    }

    public final zzil zza(zzii zzii) {
        return new zziq(zzii.zza(this.zza));
    }

    public final Object zzb() {
        return this.zza;
    }

    public final Object zzc(Object obj) {
        return this.zza;
    }

    public final boolean zzd() {
        return true;
    }
}
