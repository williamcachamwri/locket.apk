package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzwp implements ParameterizedType, Serializable {
    private final Type zza;
    private final Type zzb;
    private final Type[] zzc;

    public zzwp(Type type, Type type2, Type... typeArr) {
        Objects.requireNonNull(type2);
        if (type == null && (type2 instanceof Class)) {
            Class cls = (Class) type2;
            if (!Modifier.isStatic(cls.getModifiers()) && cls.getDeclaringClass() != null) {
                throw new IllegalArgumentException("Must specify owner type for ".concat(String.valueOf(String.valueOf(type2))));
            }
        }
        this.zza = type == null ? null : zzwr.zzc(type);
        this.zzb = zzwr.zzc(type2);
        Type[] typeArr2 = (Type[]) typeArr.clone();
        this.zzc = typeArr2;
        int length = typeArr2.length;
        for (int i = 0; i < length; i++) {
            Objects.requireNonNull(this.zzc[i]);
            zzwr.zzf(this.zzc[i]);
            Type[] typeArr3 = this.zzc;
            typeArr3[i] = zzwr.zzc(typeArr3[i]);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && zzwr.zzg(this, (ParameterizedType) obj);
    }

    public final Type[] getActualTypeArguments() {
        return (Type[]) this.zzc.clone();
    }

    public final Type getOwnerType() {
        return this.zza;
    }

    public final Type getRawType() {
        return this.zzb;
    }

    public final int hashCode() {
        int hashCode = this.zzb.hashCode() ^ Arrays.hashCode(this.zzc);
        Type type = this.zza;
        return hashCode ^ (type != null ? type.hashCode() : 0);
    }

    public final String toString() {
        int length = this.zzc.length;
        if (length == 0) {
            return zzwr.zzb(this.zzb);
        }
        StringBuilder sb = new StringBuilder((length + 1) * 30);
        sb.append(zzwr.zzb(this.zzb));
        sb.append("<");
        sb.append(zzwr.zzb(this.zzc[0]));
        for (int i = 1; i < length; i++) {
            sb.append(", ");
            sb.append(zzwr.zzb(this.zzc[i]));
        }
        sb.append(">");
        return sb.toString();
    }
}
