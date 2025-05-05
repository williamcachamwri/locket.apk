package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;
import okhttp3.HttpUrl;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzwo implements GenericArrayType, Serializable {
    private final Type zza;

    public zzwo(Type type) {
        Objects.requireNonNull(type);
        this.zza = zzwr.zzc(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && zzwr.zzg(this, (GenericArrayType) obj);
    }

    public final Type getGenericComponentType() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return String.valueOf(zzwr.zzb(this.zza)).concat(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
    }
}
