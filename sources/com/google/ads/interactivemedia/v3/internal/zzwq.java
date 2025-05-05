package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzwq implements WildcardType, Serializable {
    private final Type zza;
    private final Type zzb;

    public zzwq(Type[] typeArr, Type[] typeArr2) {
        int length = typeArr2.length;
        boolean z = true;
        zzwn.zza(length <= 1);
        zzwn.zza(typeArr.length == 1);
        if (length == 1) {
            Objects.requireNonNull(typeArr2[0]);
            zzwr.zzf(typeArr2[0]);
            zzwn.zza(typeArr[0] != Object.class ? false : z);
            this.zzb = zzwr.zzc(typeArr2[0]);
            this.zza = Object.class;
            return;
        }
        Objects.requireNonNull(typeArr[0]);
        zzwr.zzf(typeArr[0]);
        this.zzb = null;
        this.zza = zzwr.zzc(typeArr[0]);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && zzwr.zzg(this, (WildcardType) obj);
    }

    public final Type[] getLowerBounds() {
        Type type = this.zzb;
        if (type == null) {
            return zzwr.zza;
        }
        return new Type[]{type};
    }

    public final Type[] getUpperBounds() {
        return new Type[]{this.zza};
    }

    public final int hashCode() {
        Type type = this.zzb;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.zza.hashCode() + 31);
    }

    public final String toString() {
        Type type = this.zzb;
        if (type != null) {
            return "? super ".concat(String.valueOf(zzwr.zzb(type)));
        }
        Type type2 = this.zza;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends ".concat(String.valueOf(zzwr.zzb(type2)));
    }
}
