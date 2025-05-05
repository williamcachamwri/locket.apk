package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzax implements zzbc {
    private final int zza;
    private final zzbb zzb;

    zzax(int i, zzbb zzbb) {
        this.zza = i;
        this.zzb = zzbb;
    }

    public final Class annotationType() {
        return zzbc.class;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbc)) {
            return false;
        }
        zzbc zzbc = (zzbc) obj;
        return this.zza == zzbc.zza() && this.zzb.equals(zzbc.zzb());
    }

    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    public final int zza() {
        return this.zza;
    }

    public final zzbb zzb() {
        return this.zzb;
    }
}
