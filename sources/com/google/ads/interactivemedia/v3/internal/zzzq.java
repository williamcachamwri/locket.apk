package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzzq implements zzwk {
    private final zzxl zza;
    private final zzxn zzb;
    private final zzyx zzc;
    private final List zzd;
    private final int zze;

    public zzzq(zzxl zzxl, int i, zzxn zzxn, zzyx zzyx, List list) {
        this.zza = zzxl;
        this.zze = i;
        this.zzb = zzxn;
        this.zzc = zzyx;
        this.zzd = list;
    }

    static /* bridge */ /* synthetic */ void zzb(Object obj, AccessibleObject accessibleObject) {
        if (true == Modifier.isStatic(((Member) accessibleObject).getModifiers())) {
            obj = null;
        }
        if (!zzyf.zza(accessibleObject, obj)) {
            throw new zzvx(zzabp.zze(accessibleObject, true).concat(" is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type."));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x021c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01e4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x020a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.ads.interactivemedia.v3.internal.zzzo zzc(com.google.ads.interactivemedia.v3.internal.zzvr r30, com.google.ads.interactivemedia.v3.internal.zzaca r31, java.lang.Class r32, boolean r33, boolean r34) {
        /*
            r29 = this;
            r10 = r29
            r11 = r30
            r12 = r32
            boolean r0 = r32.isInterface()
            if (r0 == 0) goto L_0x000f
            com.google.ads.interactivemedia.v3.internal.zzzo r0 = com.google.ads.interactivemedia.v3.internal.zzzo.zza
            return r0
        L_0x000f:
            java.util.LinkedHashMap r13 = new java.util.LinkedHashMap
            r13.<init>()
            java.util.LinkedHashMap r14 = new java.util.LinkedHashMap
            r14.<init>()
            r15 = r31
            r0 = r33
            r9 = r12
        L_0x001e:
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            if (r9 == r1) goto L_0x024a
            java.lang.reflect.Field[] r8 = r9.getDeclaredFields()
            r7 = 1
            r6 = 0
            if (r9 == r12) goto L_0x0068
            int r1 = r8.length
            if (r1 <= 0) goto L_0x0068
            java.util.List r0 = r10.zzd
            int r0 = com.google.ads.interactivemedia.v3.internal.zzyf.zzb(r0, r9)
            r1 = 4
            if (r0 == r1) goto L_0x003f
            r1 = 3
            if (r0 != r1) goto L_0x003c
            r16 = r7
            goto L_0x006a
        L_0x003c:
            r16 = r6
            goto L_0x006a
        L_0x003f:
            com.google.ads.interactivemedia.v3.internal.zzvx r0 = new com.google.ads.interactivemedia.v3.internal.zzvx
            java.lang.String r1 = java.lang.String.valueOf(r9)
            java.lang.String r2 = java.lang.String.valueOf(r32)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "ReflectionAccessFilter does not permit using reflection for "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = " (supertype of "
            r3.append(r1)
            r3.append(r2)
            java.lang.String r1 = "). Register a TypeAdapter for this type or adjust the access filter."
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0068:
            r16 = r0
        L_0x006a:
            int r5 = r8.length
            r4 = r6
        L_0x006c:
            if (r4 >= r5) goto L_0x022c
            r3 = r8[r4]
            boolean r17 = r10.zze(r3, r7)
            boolean r0 = r10.zze(r3, r6)
            if (r17 != 0) goto L_0x008b
            if (r0 != 0) goto L_0x008a
            r19 = r4
            r27 = r5
            r28 = r6
            r24 = r7
            r25 = r8
            r31 = r9
            goto L_0x021c
        L_0x008a:
            r0 = r7
        L_0x008b:
            r1 = 0
            if (r34 == 0) goto L_0x00d7
            int r2 = r3.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            if (r2 == 0) goto L_0x009c
            r7 = r1
            r18 = r6
            goto L_0x00da
        L_0x009c:
            java.lang.reflect.Method r2 = com.google.ads.interactivemedia.v3.internal.zzabp.zzh(r9, r3)
            if (r16 != 0) goto L_0x00a5
            com.google.ads.interactivemedia.v3.internal.zzabp.zzi(r2)
        L_0x00a5:
            java.lang.Class<com.google.ads.interactivemedia.v3.internal.zzwm> r7 = com.google.ads.interactivemedia.v3.internal.zzwm.class
            java.lang.annotation.Annotation r7 = r2.getAnnotation(r7)
            if (r7 == 0) goto L_0x00d3
            java.lang.Class<com.google.ads.interactivemedia.v3.internal.zzwm> r7 = com.google.ads.interactivemedia.v3.internal.zzwm.class
            java.lang.annotation.Annotation r7 = r3.getAnnotation(r7)
            if (r7 == 0) goto L_0x00b6
            goto L_0x00d3
        L_0x00b6:
            java.lang.String r0 = com.google.ads.interactivemedia.v3.internal.zzabp.zze(r2, r6)
            com.google.ads.interactivemedia.v3.internal.zzvx r1 = new com.google.ads.interactivemedia.v3.internal.zzvx
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "@SerializedName on "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = " is not supported"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>((java.lang.String) r0)
            throw r1
        L_0x00d3:
            r18 = r0
            r7 = r2
            goto L_0x00da
        L_0x00d7:
            r18 = r0
            r7 = r1
        L_0x00da:
            if (r16 != 0) goto L_0x00e1
            if (r7 != 0) goto L_0x00e1
            com.google.ads.interactivemedia.v3.internal.zzabp.zzi(r3)
        L_0x00e1:
            java.lang.reflect.Type r0 = r15.zzd()
            java.lang.reflect.Type r2 = r3.getGenericType()
            java.lang.reflect.Type r0 = com.google.ads.interactivemedia.v3.internal.zzwr.zze(r0, r9, r2)
            java.lang.Class<com.google.ads.interactivemedia.v3.internal.zzwm> r2 = com.google.ads.interactivemedia.v3.internal.zzwm.class
            java.lang.annotation.Annotation r2 = r3.getAnnotation(r2)
            com.google.ads.interactivemedia.v3.internal.zzwm r2 = (com.google.ads.interactivemedia.v3.internal.zzwm) r2
            if (r2 != 0) goto L_0x010d
            int r2 = r10.zze
            if (r2 == 0) goto L_0x010c
            int r2 = r2 + -1
            if (r2 != 0) goto L_0x010c
            java.lang.String r2 = r3.getName()
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r20 = r4
            r4 = r6
            r6 = r2
            goto L_0x0131
        L_0x010c:
            throw r1
        L_0x010d:
            java.lang.String r1 = r2.zza()
            java.lang.String[] r2 = r2.zzb()
            int r6 = r2.length
            if (r6 != 0) goto L_0x0120
            java.util.List r2 = java.util.Collections.singletonList(r1)
            r6 = r2
            r20 = r4
            goto L_0x0130
        L_0x0120:
            int r6 = r6 + 1
            r20 = r4
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r6)
            r4.add(r1)
            java.util.Collections.addAll(r4, r2)
            r6 = r4
        L_0x0130:
            r4 = 0
        L_0x0131:
            java.lang.Object r1 = r6.get(r4)
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            com.google.ads.interactivemedia.v3.internal.zzaca r1 = com.google.ads.interactivemedia.v3.internal.zzaca.zzb(r0)
            java.lang.Class r0 = r1.zzc()
            boolean r4 = r0 instanceof java.lang.Class
            if (r4 == 0) goto L_0x0150
            r4 = r0
            java.lang.Class r4 = (java.lang.Class) r4
            boolean r0 = r0.isPrimitive()
            if (r0 == 0) goto L_0x0150
            r21 = 1
            goto L_0x0152
        L_0x0150:
            r21 = 0
        L_0x0152:
            int r0 = r3.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r0)
            if (r4 == 0) goto L_0x0165
            boolean r0 = java.lang.reflect.Modifier.isFinal(r0)
            if (r0 == 0) goto L_0x0165
            r22 = 1
            goto L_0x0167
        L_0x0165:
            r22 = 0
        L_0x0167:
            java.lang.Class<com.google.ads.interactivemedia.v3.internal.zzwl> r0 = com.google.ads.interactivemedia.v3.internal.zzwl.class
            java.lang.annotation.Annotation r0 = r3.getAnnotation(r0)
            r4 = r0
            com.google.ads.interactivemedia.v3.internal.zzwl r4 = (com.google.ads.interactivemedia.v3.internal.zzwl) r4
            if (r4 == 0) goto L_0x0191
            com.google.ads.interactivemedia.v3.internal.zzyx r0 = r10.zzc
            r23 = r1
            com.google.ads.interactivemedia.v3.internal.zzxl r1 = r10.zza
            r24 = 0
            r25 = r23
            r23 = r2
            r2 = r30
            r26 = r3
            r3 = r25
            r19 = r20
            r20 = 0
            r27 = r5
            r5 = r24
            com.google.ads.interactivemedia.v3.internal.zzwj r1 = r0.zzb(r1, r2, r3, r4, r5)
            goto L_0x019e
        L_0x0191:
            r25 = r1
            r23 = r2
            r26 = r3
            r27 = r5
            r19 = r20
            r20 = 0
            r1 = 0
        L_0x019e:
            if (r1 != 0) goto L_0x01a8
            r0 = r25
            com.google.ads.interactivemedia.v3.internal.zzwj r2 = r11.zza(r0)
            r5 = r2
            goto L_0x01ab
        L_0x01a8:
            r0 = r25
            r5 = r1
        L_0x01ab:
            if (r17 == 0) goto L_0x01bc
            if (r1 == 0) goto L_0x01b0
            goto L_0x01bc
        L_0x01b0:
            com.google.ads.interactivemedia.v3.internal.zzzw r1 = new com.google.ads.interactivemedia.v3.internal.zzzw
            java.lang.reflect.Type r0 = r0.zzd()
            r1.<init>(r11, r5, r0)
            r24 = r1
            goto L_0x01be
        L_0x01bc:
            r24 = r5
        L_0x01be:
            com.google.ads.interactivemedia.v3.internal.zzzk r4 = new com.google.ads.interactivemedia.v3.internal.zzzk
            r0 = r4
            r1 = r29
            r2 = r23
            r3 = r26
            r10 = r4
            r4 = r16
            r25 = r5
            r5 = r7
            r28 = r20
            r20 = r6
            r6 = r24
            r24 = 1
            r7 = r25
            r25 = r8
            r8 = r21
            r31 = r9
            r9 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            if (r18 == 0) goto L_0x0206
            java.util.Iterator r0 = r20.iterator()
        L_0x01e8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0206
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r13.put(r1, r10)
            com.google.ads.interactivemedia.v3.internal.zzzm r2 = (com.google.ads.interactivemedia.v3.internal.zzzm) r2
            if (r2 != 0) goto L_0x01fd
            goto L_0x01e8
        L_0x01fd:
            java.lang.reflect.Field r0 = r2.zzh
            r2 = r26
            java.lang.IllegalArgumentException r0 = zzd(r12, r1, r0, r2)
            throw r0
        L_0x0206:
            r2 = r26
            if (r17 == 0) goto L_0x021c
            r1 = r23
            java.lang.Object r0 = r14.put(r1, r10)
            com.google.ads.interactivemedia.v3.internal.zzzm r0 = (com.google.ads.interactivemedia.v3.internal.zzzm) r0
            if (r0 != 0) goto L_0x0215
            goto L_0x021c
        L_0x0215:
            java.lang.reflect.Field r0 = r0.zzh
            java.lang.IllegalArgumentException r0 = zzd(r12, r1, r0, r2)
            throw r0
        L_0x021c:
            int r4 = r19 + 1
            r10 = r29
            r9 = r31
            r7 = r24
            r8 = r25
            r5 = r27
            r6 = r28
            goto L_0x006c
        L_0x022c:
            r31 = r9
            java.lang.reflect.Type r0 = r15.zzd()
            java.lang.reflect.Type r1 = r31.getGenericSuperclass()
            r2 = r31
            java.lang.reflect.Type r0 = com.google.ads.interactivemedia.v3.internal.zzwr.zze(r0, r2, r1)
            com.google.ads.interactivemedia.v3.internal.zzaca r15 = com.google.ads.interactivemedia.v3.internal.zzaca.zzb(r0)
            java.lang.Class r9 = r15.zzc()
            r10 = r29
            r0 = r16
            goto L_0x001e
        L_0x024a:
            com.google.ads.interactivemedia.v3.internal.zzzo r0 = new com.google.ads.interactivemedia.v3.internal.zzzo
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Collection r2 = r14.values()
            r1.<init>(r2)
            r0.<init>(r13, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzzq.zzc(com.google.ads.interactivemedia.v3.internal.zzvr, com.google.ads.interactivemedia.v3.internal.zzaca, java.lang.Class, boolean, boolean):com.google.ads.interactivemedia.v3.internal.zzzo");
    }

    private static IllegalArgumentException zzd(Class cls, String str, Field field, Field field2) {
        String name = cls.getName();
        String zzd2 = zzabp.zzd(field);
        String zzd3 = zzabp.zzd(field2);
        throw new IllegalArgumentException("Class " + name + " declares multiple JSON fields named '" + str + "'; conflict is caused by fields " + zzd2 + " and " + zzd3 + "\nSee https://github.com/google/gson/blob/main/Troubleshooting.md#duplicate-fields");
    }

    private final boolean zze(Field field, boolean z) {
        return !this.zzb.zzd(field, z);
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        Class zzc2 = zzaca.zzc();
        if (!Object.class.isAssignableFrom(zzc2)) {
            return null;
        }
        if (zzabp.zzj(zzc2)) {
            return new zzzj(this);
        }
        int zzb2 = zzyf.zzb(this.zzd, zzc2);
        if (zzb2 != 4) {
            boolean z = zzb2 == 3;
            if (zzabp.zzk(zzc2)) {
                return new zzzp(zzc2, zzc(zzvr, zzaca, zzc2, z, true), z);
            }
            return new zzzn(this.zza.zza(zzaca), zzc(zzvr, zzaca, zzc2, z, false));
        }
        String valueOf = String.valueOf(zzc2);
        throw new zzvx("ReflectionAccessFilter does not permit using reflection for " + valueOf + ". Register a TypeAdapter for this type or adjust the access filter.");
    }
}
