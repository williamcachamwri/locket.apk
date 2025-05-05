package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzwr {
    static final Type[] zza = new Type[0];

    public static Class zza(Type type) {
        String str;
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            zzwn.zza(rawType instanceof Class);
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(zza(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return zza(((WildcardType) type).getUpperBounds()[0]);
            }
            if (type == null) {
                str = "null";
            } else {
                str = type.getClass().getName();
            }
            String valueOf = String.valueOf(type);
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + valueOf + "> is of type " + str);
        }
    }

    public static String zzb(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    public static Type zzc(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new zzwo(zzc(cls.getComponentType())) : cls;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new zzwp(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new zzwo(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (!(type instanceof WildcardType)) {
                return type;
            }
            WildcardType wildcardType = (WildcardType) type;
            return new zzwq(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
    }

    public static Type zzd(Type type, Class cls) {
        Type zzj = zzj(type, cls, Collection.class);
        return zzj instanceof ParameterizedType ? ((ParameterizedType) zzj).getActualTypeArguments()[0] : Object.class;
    }

    public static Type zze(Type type, Class cls, Type type2) {
        return zzk(type, cls, type2, new HashMap());
    }

    static void zzf(Type type) {
        boolean z = true;
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            z = false;
        }
        zzwn.zza(z);
    }

    public static boolean zzg(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (!Objects.equals(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments())) {
                return false;
            }
            return true;
        } else if (type instanceof GenericArrayType) {
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return zzg(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || !Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                return false;
            }
            return true;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (!Objects.equals(typeVariable.getGenericDeclaration(), typeVariable2.getGenericDeclaration()) || !typeVariable.getName().equals(typeVariable2.getName())) {
                return false;
            }
            return true;
        }
    }

    public static Type[] zzh(Type type, Class cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type zzj = zzj(type, cls, Map.class);
        if (zzj instanceof ParameterizedType) {
            return ((ParameterizedType) zzj).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    private static Type zzi(Type type, Class<? super Object> cls, Class cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                Class cls3 = interfaces[i];
                if (cls3 == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(cls3)) {
                    return zzi(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<? super Object> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return zzi(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    private static Type zzj(Type type, Class cls, Class cls2) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        zzwn.zza(cls2.isAssignableFrom(cls));
        return zze(type, cls, zzi(type, cls, cls2));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: java.lang.reflect.GenericArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: java.lang.reflect.ParameterizedType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.lang.reflect.Type[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: com.google.ads.interactivemedia.v3.internal.zzwo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: com.google.ads.interactivemedia.v3.internal.zzwo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.reflect.TypeVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v16, resolved type: com.google.ads.interactivemedia.v3.internal.zzwo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: com.google.ads.interactivemedia.v3.internal.zzwo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v24, resolved type: java.lang.reflect.GenericArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v25, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v26, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v27, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v28, resolved type: com.google.ads.interactivemedia.v3.internal.zzwq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v29, resolved type: com.google.ads.interactivemedia.v3.internal.zzwq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v30, resolved type: java.lang.reflect.WildcardType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v31, resolved type: com.google.ads.interactivemedia.v3.internal.zzwp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v32, resolved type: java.lang.reflect.GenericArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v33, resolved type: com.google.ads.interactivemedia.v3.internal.zzwo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v34, resolved type: com.google.ads.interactivemedia.v3.internal.zzwo} */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d9, code lost:
        if (r5 != false) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00db, code lost:
        r12 = new com.google.ads.interactivemedia.v3.internal.zzwp(r4, r12.getRawType(), r7);
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0140 A[EDGE_INSN: B:80:0x0140->B:75:0x0140 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.reflect.Type zzk(java.lang.reflect.Type r10, java.lang.Class r11, java.lang.reflect.Type r12, java.util.Map r13) {
        /*
            r0 = 0
            r1 = r0
        L_0x0002:
            boolean r2 = r12 instanceof java.lang.reflect.TypeVariable
            r3 = 0
            if (r2 == 0) goto L_0x005d
            r2 = r12
            java.lang.reflect.TypeVariable r2 = (java.lang.reflect.TypeVariable) r2
            java.lang.Object r4 = r13.get(r2)
            java.lang.reflect.Type r4 = (java.lang.reflect.Type) r4
            if (r4 == 0) goto L_0x0018
            java.lang.Class r10 = java.lang.Void.TYPE
            if (r4 != r10) goto L_0x0017
            return r12
        L_0x0017:
            return r4
        L_0x0018:
            java.lang.Class r12 = java.lang.Void.TYPE
            r13.put(r2, r12)
            if (r1 != 0) goto L_0x0020
            r1 = r2
        L_0x0020:
            java.lang.reflect.GenericDeclaration r12 = r2.getGenericDeclaration()
            boolean r4 = r12 instanceof java.lang.Class
            if (r4 == 0) goto L_0x002b
            java.lang.Class r12 = (java.lang.Class) r12
            goto L_0x002c
        L_0x002b:
            r12 = r0
        L_0x002c:
            if (r12 != 0) goto L_0x002f
            goto L_0x0058
        L_0x002f:
            java.lang.reflect.Type r4 = zzi(r10, r11, r12)
            boolean r5 = r4 instanceof java.lang.reflect.ParameterizedType
            if (r5 == 0) goto L_0x0058
            java.lang.reflect.TypeVariable[] r12 = r12.getTypeParameters()
            int r5 = r12.length
        L_0x003c:
            if (r3 >= r5) goto L_0x0052
            r6 = r12[r3]
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x004f
            java.lang.reflect.ParameterizedType r4 = (java.lang.reflect.ParameterizedType) r4
            java.lang.reflect.Type[] r12 = r4.getActualTypeArguments()
            r12 = r12[r3]
            goto L_0x0059
        L_0x004f:
            int r3 = r3 + 1
            goto L_0x003c
        L_0x0052:
            java.util.NoSuchElementException r10 = new java.util.NoSuchElementException
            r10.<init>()
            throw r10
        L_0x0058:
            r12 = r2
        L_0x0059:
            if (r12 != r2) goto L_0x0002
            goto L_0x0140
        L_0x005d:
            boolean r0 = r12 instanceof java.lang.Class
            if (r0 == 0) goto L_0x0082
            r0 = r12
            java.lang.Class r0 = (java.lang.Class) r0
            boolean r2 = r0.isArray()
            if (r2 == 0) goto L_0x0082
            java.lang.Class r12 = r0.getComponentType()
            java.lang.reflect.Type r10 = zzk(r10, r11, r12, r13)
            boolean r11 = java.util.Objects.equals(r12, r10)
            if (r11 == 0) goto L_0x007b
            r12 = r0
            goto L_0x0140
        L_0x007b:
            com.google.ads.interactivemedia.v3.internal.zzwo r12 = new com.google.ads.interactivemedia.v3.internal.zzwo
            r12.<init>(r10)
            goto L_0x0140
        L_0x0082:
            boolean r0 = r12 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x009d
            java.lang.reflect.GenericArrayType r12 = (java.lang.reflect.GenericArrayType) r12
            java.lang.reflect.Type r0 = r12.getGenericComponentType()
            java.lang.reflect.Type r10 = zzk(r10, r11, r0, r13)
            boolean r11 = java.util.Objects.equals(r0, r10)
            if (r11 != 0) goto L_0x0140
            com.google.ads.interactivemedia.v3.internal.zzwo r12 = new com.google.ads.interactivemedia.v3.internal.zzwo
            r12.<init>(r10)
            goto L_0x0140
        L_0x009d:
            boolean r0 = r12 instanceof java.lang.reflect.ParameterizedType
            r2 = 1
            if (r0 == 0) goto L_0x00e5
            java.lang.reflect.ParameterizedType r12 = (java.lang.reflect.ParameterizedType) r12
            java.lang.reflect.Type r0 = r12.getOwnerType()
            java.lang.reflect.Type r4 = zzk(r10, r11, r0, r13)
            boolean r0 = java.util.Objects.equals(r4, r0)
            r0 = r0 ^ r2
            java.lang.reflect.Type[] r5 = r12.getActualTypeArguments()
            int r6 = r5.length
            r7 = r5
            r5 = r3
        L_0x00b8:
            if (r3 >= r6) goto L_0x00d7
            r8 = r7[r3]
            java.lang.reflect.Type r8 = zzk(r10, r11, r8, r13)
            r9 = r7[r3]
            boolean r9 = java.util.Objects.equals(r8, r9)
            if (r9 != 0) goto L_0x00d4
            if (r5 != 0) goto L_0x00d1
            java.lang.Object r5 = r7.clone()
            r7 = r5
            java.lang.reflect.Type[] r7 = (java.lang.reflect.Type[]) r7
        L_0x00d1:
            r7[r3] = r8
            r5 = r2
        L_0x00d4:
            int r3 = r3 + 1
            goto L_0x00b8
        L_0x00d7:
            if (r0 != 0) goto L_0x00db
            if (r5 == 0) goto L_0x0140
        L_0x00db:
            java.lang.reflect.Type r10 = r12.getRawType()
            com.google.ads.interactivemedia.v3.internal.zzwp r12 = new com.google.ads.interactivemedia.v3.internal.zzwp
            r12.<init>(r4, r10, r7)
            goto L_0x0140
        L_0x00e5:
            boolean r0 = r12 instanceof java.lang.reflect.WildcardType
            if (r0 == 0) goto L_0x0140
            java.lang.reflect.WildcardType r12 = (java.lang.reflect.WildcardType) r12
            java.lang.reflect.Type[] r0 = r12.getLowerBounds()
            java.lang.reflect.Type[] r4 = r12.getUpperBounds()
            int r5 = r0.length
            if (r5 != r2) goto L_0x011c
            r4 = r0[r3]
            java.lang.reflect.Type r10 = zzk(r10, r11, r4, r13)
            r11 = r0[r3]
            if (r10 == r11) goto L_0x0140
            boolean r11 = r10 instanceof java.lang.reflect.WildcardType
            if (r11 == 0) goto L_0x010b
            java.lang.reflect.WildcardType r10 = (java.lang.reflect.WildcardType) r10
            java.lang.reflect.Type[] r10 = r10.getLowerBounds()
            goto L_0x0110
        L_0x010b:
            java.lang.reflect.Type[] r11 = new java.lang.reflect.Type[r2]
            r11[r3] = r10
            r10 = r11
        L_0x0110:
            com.google.ads.interactivemedia.v3.internal.zzwq r12 = new com.google.ads.interactivemedia.v3.internal.zzwq
            java.lang.reflect.Type[] r11 = new java.lang.reflect.Type[r2]
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            r11[r3] = r0
            r12.<init>(r11, r10)
            goto L_0x0140
        L_0x011c:
            int r0 = r4.length
            if (r0 != r2) goto L_0x0140
            r0 = r4[r3]
            java.lang.reflect.Type r10 = zzk(r10, r11, r0, r13)
            r11 = r4[r3]
            if (r10 == r11) goto L_0x0140
            boolean r11 = r10 instanceof java.lang.reflect.WildcardType
            if (r11 == 0) goto L_0x0134
            java.lang.reflect.WildcardType r10 = (java.lang.reflect.WildcardType) r10
            java.lang.reflect.Type[] r10 = r10.getUpperBounds()
            goto L_0x0139
        L_0x0134:
            java.lang.reflect.Type[] r11 = new java.lang.reflect.Type[r2]
            r11[r3] = r10
            r10 = r11
        L_0x0139:
            com.google.ads.interactivemedia.v3.internal.zzwq r12 = new com.google.ads.interactivemedia.v3.internal.zzwq
            java.lang.reflect.Type[] r11 = zza
            r12.<init>(r10, r11)
        L_0x0140:
            if (r1 == 0) goto L_0x0145
            r13.put(r1, r12)
        L_0x0145:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzwr.zzk(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type, java.util.Map):java.lang.reflect.Type");
    }
}
