package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzaca {
    private final Class zza;
    private final Type zzb;
    private final int zzc;

    protected zzaca() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Class<zzaca> cls = zzaca.class;
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            if (parameterizedType.getRawType() == cls) {
                Type zzc2 = zzwr.zzc(parameterizedType.getActualTypeArguments()[0]);
                if (!Objects.equals(System.getProperty("gson.allowCapturingTypeVariables"), "true")) {
                    zze(zzc2);
                }
                this.zzb = zzc2;
                this.zza = zzwr.zza(zzc2);
                this.zzc = zzc2.hashCode();
                return;
            }
        } else if (genericSuperclass == cls) {
            throw new IllegalStateException("TypeToken must be created with a type argument: new TypeToken<...>() {}; When using code shrinkers (ProGuard, R8, ...) make sure that generic signatures are preserved.\nSee https://github.com/google/gson/blob/main/Troubleshooting.md#type-token-raw");
        }
        throw new IllegalStateException("Must only create direct subclasses of TypeToken");
    }

    public static zzaca zza(Class cls) {
        return new zzaca(cls);
    }

    public static zzaca zzb(Type type) {
        return new zzaca(type);
    }

    private static void zze(Type type) {
        if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            throw new IllegalArgumentException("TypeToken type argument must not contain a type variable; captured type variable " + typeVariable.getName() + " declared by " + String.valueOf(typeVariable.getGenericDeclaration()) + "\nSee https://github.com/google/gson/blob/main/Troubleshooting.md#typetoken-type-variable");
        } else if (type instanceof GenericArrayType) {
            zze(((GenericArrayType) type).getGenericComponentType());
        } else {
            int i = 0;
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type ownerType = parameterizedType.getOwnerType();
                if (ownerType != null) {
                    zze(ownerType);
                }
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                int length = actualTypeArguments.length;
                while (i < length) {
                    zze(actualTypeArguments[i]);
                    i++;
                }
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                for (Type zze : wildcardType.getLowerBounds()) {
                    zze(zze);
                }
                Type[] upperBounds = wildcardType.getUpperBounds();
                int length2 = upperBounds.length;
                while (i < length2) {
                    zze(upperBounds[i]);
                    i++;
                }
            } else if (type == null) {
                throw new IllegalArgumentException("TypeToken captured `null` as type argument; probably a compiler / runtime bug");
            }
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzaca) && zzwr.zzg(this.zzb, ((zzaca) obj).zzb);
    }

    public final int hashCode() {
        return this.zzc;
    }

    public final String toString() {
        return zzwr.zzb(this.zzb);
    }

    public final Class zzc() {
        return this.zza;
    }

    public final Type zzd() {
        return this.zzb;
    }

    private zzaca(Type type) {
        Type zzc2 = zzwr.zzc((Type) Objects.requireNonNull(type));
        this.zzb = zzc2;
        this.zza = zzwr.zza(zzc2);
        this.zzc = zzc2.hashCode();
    }
}
