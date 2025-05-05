package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzxl {
    private final Map zza;
    private final List zzb;

    public zzxl(Map map, boolean z, List list) {
        this.zza = map;
        this.zzb = list;
    }

    static String zzb(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(String.valueOf(cls.getName()));
        }
        if (!Modifier.isAbstract(modifiers)) {
            return null;
        }
        String name = cls.getName();
        return "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + name + "\nSee https://github.com/google/gson/blob/main/Troubleshooting.md#r8-abstract-class";
    }

    public final String toString() {
        return this.zza.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.zzya zza(com.google.ads.interactivemedia.v3.internal.zzaca r9) {
        /*
            r8 = this;
            java.util.Map r0 = r8.zza
            java.lang.reflect.Type r1 = r9.zzd()
            java.lang.Class r9 = r9.zzc()
            java.lang.Object r0 = r0.get(r1)
            com.google.ads.interactivemedia.v3.internal.zzvt r0 = (com.google.ads.interactivemedia.v3.internal.zzvt) r0
            if (r0 == 0) goto L_0x0018
            com.google.ads.interactivemedia.v3.internal.zzxc r9 = new com.google.ads.interactivemedia.v3.internal.zzxc
            r9.<init>(r8, r0, r1)
            return r9
        L_0x0018:
            java.util.Map r0 = r8.zza
            java.lang.Object r0 = r0.get(r9)
            com.google.ads.interactivemedia.v3.internal.zzvt r0 = (com.google.ads.interactivemedia.v3.internal.zzvt) r0
            if (r0 != 0) goto L_0x016d
            java.lang.Class<java.util.EnumSet> r0 = java.util.EnumSet.class
            boolean r0 = r0.isAssignableFrom(r9)
            r2 = 0
            if (r0 == 0) goto L_0x0031
            com.google.ads.interactivemedia.v3.internal.zzxg r0 = new com.google.ads.interactivemedia.v3.internal.zzxg
            r0.<init>(r1)
            goto L_0x003c
        L_0x0031:
            java.lang.Class<java.util.EnumMap> r0 = java.util.EnumMap.class
            if (r9 != r0) goto L_0x003b
            com.google.ads.interactivemedia.v3.internal.zzxh r0 = new com.google.ads.interactivemedia.v3.internal.zzxh
            r0.<init>(r1)
            goto L_0x003c
        L_0x003b:
            r0 = r2
        L_0x003c:
            if (r0 == 0) goto L_0x003f
            return r0
        L_0x003f:
            java.util.List r0 = r8.zzb
            int r0 = com.google.ads.interactivemedia.v3.internal.zzyf.zzb(r0, r9)
            int r3 = r9.getModifiers()
            boolean r3 = java.lang.reflect.Modifier.isAbstract(r3)
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0053
        L_0x0051:
            r6 = r2
            goto L_0x00a3
        L_0x0053:
            java.lang.Class[] r3 = new java.lang.Class[r4]     // Catch:{ NoSuchMethodException -> 0x0051 }
            java.lang.reflect.Constructor r3 = r9.getDeclaredConstructor(r3)     // Catch:{ NoSuchMethodException -> 0x0051 }
            if (r0 == r5) goto L_0x008e
            com.google.ads.interactivemedia.v3.internal.zzye r6 = com.google.ads.interactivemedia.v3.internal.zzye.zzb
            boolean r6 = r6.zza(r3, r2)
            if (r6 == 0) goto L_0x0071
            r6 = 4
            if (r0 != r6) goto L_0x008e
            int r6 = r3.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isPublic(r6)
            if (r6 == 0) goto L_0x0071
            goto L_0x008e
        L_0x0071:
            java.lang.String r3 = java.lang.String.valueOf(r9)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Unable to invoke no-args constructor of "
            r6.<init>(r7)
            r6.append(r3)
            java.lang.String r3 = "; constructor is not accessible and ReflectionAccessFilter does not permit making it accessible. Register an InstanceCreator or a TypeAdapter for this type, change the visibility of the constructor or adjust the access filter."
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            com.google.ads.interactivemedia.v3.internal.zzxi r6 = new com.google.ads.interactivemedia.v3.internal.zzxi
            r6.<init>(r3)
            goto L_0x00a3
        L_0x008e:
            if (r0 != r5) goto L_0x009e
            java.lang.String r6 = com.google.ads.interactivemedia.v3.internal.zzabp.zzf(r3)
            if (r6 == 0) goto L_0x009d
            com.google.ads.interactivemedia.v3.internal.zzxj r3 = new com.google.ads.interactivemedia.v3.internal.zzxj
            r3.<init>(r6)
            r6 = r3
            goto L_0x00a3
        L_0x009d:
            r0 = r5
        L_0x009e:
            com.google.ads.interactivemedia.v3.internal.zzxk r6 = new com.google.ads.interactivemedia.v3.internal.zzxk
            r6.<init>(r3)
        L_0x00a3:
            if (r6 != 0) goto L_0x016c
            java.lang.Class<java.util.Collection> r3 = java.util.Collection.class
            boolean r3 = r3.isAssignableFrom(r9)
            if (r3 == 0) goto L_0x00df
            java.lang.Class<java.util.SortedSet> r1 = java.util.SortedSet.class
            boolean r1 = r1.isAssignableFrom(r9)
            if (r1 == 0) goto L_0x00bc
            com.google.ads.interactivemedia.v3.internal.zzws r2 = new com.google.ads.interactivemedia.v3.internal.zzws
            r2.<init>()
            goto L_0x0138
        L_0x00bc:
            java.lang.Class<java.util.Set> r1 = java.util.Set.class
            boolean r1 = r1.isAssignableFrom(r9)
            if (r1 == 0) goto L_0x00cb
            com.google.ads.interactivemedia.v3.internal.zzwt r2 = new com.google.ads.interactivemedia.v3.internal.zzwt
            r2.<init>()
            goto L_0x0138
        L_0x00cb:
            java.lang.Class<java.util.Queue> r1 = java.util.Queue.class
            boolean r1 = r1.isAssignableFrom(r9)
            if (r1 == 0) goto L_0x00d9
            com.google.ads.interactivemedia.v3.internal.zzwu r2 = new com.google.ads.interactivemedia.v3.internal.zzwu
            r2.<init>()
            goto L_0x0138
        L_0x00d9:
            com.google.ads.interactivemedia.v3.internal.zzwv r2 = new com.google.ads.interactivemedia.v3.internal.zzwv
            r2.<init>()
            goto L_0x0138
        L_0x00df:
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r9)
            if (r3 == 0) goto L_0x0138
            java.lang.Class<java.util.concurrent.ConcurrentNavigableMap> r2 = java.util.concurrent.ConcurrentNavigableMap.class
            boolean r2 = r2.isAssignableFrom(r9)
            if (r2 == 0) goto L_0x00f5
            com.google.ads.interactivemedia.v3.internal.zzww r2 = new com.google.ads.interactivemedia.v3.internal.zzww
            r2.<init>()
            goto L_0x0138
        L_0x00f5:
            java.lang.Class<java.util.concurrent.ConcurrentMap> r2 = java.util.concurrent.ConcurrentMap.class
            boolean r2 = r2.isAssignableFrom(r9)
            if (r2 == 0) goto L_0x0103
            com.google.ads.interactivemedia.v3.internal.zzwx r2 = new com.google.ads.interactivemedia.v3.internal.zzwx
            r2.<init>()
            goto L_0x0138
        L_0x0103:
            java.lang.Class<java.util.SortedMap> r2 = java.util.SortedMap.class
            boolean r2 = r2.isAssignableFrom(r9)
            if (r2 == 0) goto L_0x0111
            com.google.ads.interactivemedia.v3.internal.zzwy r2 = new com.google.ads.interactivemedia.v3.internal.zzwy
            r2.<init>()
            goto L_0x0138
        L_0x0111:
            boolean r2 = r1 instanceof java.lang.reflect.ParameterizedType
            if (r2 == 0) goto L_0x0133
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1
            java.lang.reflect.Type[] r1 = r1.getActualTypeArguments()
            r1 = r1[r4]
            com.google.ads.interactivemedia.v3.internal.zzaca r1 = com.google.ads.interactivemedia.v3.internal.zzaca.zzb(r1)
            java.lang.Class r1 = r1.zzc()
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            boolean r1 = r2.isAssignableFrom(r1)
            if (r1 != 0) goto L_0x0133
            com.google.ads.interactivemedia.v3.internal.zzwz r2 = new com.google.ads.interactivemedia.v3.internal.zzwz
            r2.<init>()
            goto L_0x0138
        L_0x0133:
            com.google.ads.interactivemedia.v3.internal.zzxa r2 = new com.google.ads.interactivemedia.v3.internal.zzxa
            r2.<init>()
        L_0x0138:
            if (r2 == 0) goto L_0x013b
            return r2
        L_0x013b:
            java.lang.String r1 = zzb(r9)
            if (r1 == 0) goto L_0x0147
            com.google.ads.interactivemedia.v3.internal.zzxe r9 = new com.google.ads.interactivemedia.v3.internal.zzxe
            r9.<init>(r8, r1)
            return r9
        L_0x0147:
            if (r0 != r5) goto L_0x014f
            com.google.ads.interactivemedia.v3.internal.zzxb r0 = new com.google.ads.interactivemedia.v3.internal.zzxb
            r0.<init>(r8, r9)
            return r0
        L_0x014f:
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unable to create instance of "
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r9 = "; ReflectionAccessFilter does not permit using reflection or Unsafe. Register an InstanceCreator or a TypeAdapter for this type or adjust the access filter to allow using reflection."
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            com.google.ads.interactivemedia.v3.internal.zzxf r0 = new com.google.ads.interactivemedia.v3.internal.zzxf
            r0.<init>(r8, r9)
            return r0
        L_0x016c:
            return r6
        L_0x016d:
            com.google.ads.interactivemedia.v3.internal.zzxd r9 = new com.google.ads.interactivemedia.v3.internal.zzxd
            r9.<init>(r8, r0, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzxl.zza(com.google.ads.interactivemedia.v3.internal.zzaca):com.google.ads.interactivemedia.v3.internal.zzya");
    }
}
