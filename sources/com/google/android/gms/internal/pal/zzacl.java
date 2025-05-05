package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzacl {
    private final Object zza;
    private final int zzb;

    zzacl(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzacl)) {
            return false;
        }
        zzacl zzacl = (zzacl) obj;
        if (this.zza == zzacl.zza && this.zzb == zzacl.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
