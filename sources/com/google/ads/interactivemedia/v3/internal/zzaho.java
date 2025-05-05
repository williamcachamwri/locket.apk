package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzaho {
    private static final ThreadLocal zza = new ThreadLocal();
    private boolean zzb = true;
    private final List zzc;
    private String[] zzd;

    public zzaho() {
        ArrayList arrayList = new ArrayList(1);
        this.zzc = arrayList;
        arrayList.add(String.class);
    }

    static Set zza() {
        return (Set) zza.get();
    }

    static zzahw zze(Object obj, Object obj2) {
        return zzahv.zzc(new zzaht(obj), new zzaht(obj2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        if (r5.isInstance(r3) == false) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r7 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        if (r6.isInstance(r2) == false) goto L_0x0036;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzf(java.lang.Object r2, java.lang.Object r3, boolean r4, java.lang.Class r5, boolean r6, java.lang.String... r7) {
        /*
            if (r2 != r3) goto L_0x0004
            r2 = 1
            return r2
        L_0x0004:
            com.google.ads.interactivemedia.v3.internal.zzaho r4 = new com.google.ads.interactivemedia.v3.internal.zzaho
            r4.<init>()
            r4.zzd = r7
            boolean r5 = r4.zzb
            if (r5 != 0) goto L_0x0010
            goto L_0x006e
        L_0x0010:
            if (r2 == r3) goto L_0x006e
            java.lang.Class r5 = r2.getClass()
            java.lang.Class r6 = r3.getClass()
            boolean r7 = r5.isInstance(r3)
            r0 = 0
            if (r7 == 0) goto L_0x0028
            boolean r7 = r6.isInstance(r2)
            if (r7 != 0) goto L_0x0034
            goto L_0x0036
        L_0x0028:
            boolean r7 = r6.isInstance(r2)
            if (r7 == 0) goto L_0x006c
            boolean r7 = r5.isInstance(r3)
            if (r7 != 0) goto L_0x0036
        L_0x0034:
            r7 = r5
            goto L_0x0037
        L_0x0036:
            r7 = r6
        L_0x0037:
            boolean r1 = r7.isArray()     // Catch:{ IllegalArgumentException -> 0x006c }
            if (r1 == 0) goto L_0x0041
            r4.zzd(r2, r3)     // Catch:{ IllegalArgumentException -> 0x006c }
            goto L_0x006e
        L_0x0041:
            java.util.List r1 = r4.zzc     // Catch:{ IllegalArgumentException -> 0x006c }
            boolean r5 = r1.contains(r5)     // Catch:{ IllegalArgumentException -> 0x006c }
            if (r5 != 0) goto L_0x0065
            java.util.List r5 = r4.zzc     // Catch:{ IllegalArgumentException -> 0x006c }
            boolean r5 = r5.contains(r6)     // Catch:{ IllegalArgumentException -> 0x006c }
            if (r5 == 0) goto L_0x0052
            goto L_0x0065
        L_0x0052:
            r4.zzg(r2, r3, r7)     // Catch:{ IllegalArgumentException -> 0x006c }
        L_0x0055:
            java.lang.Class r5 = r7.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x006c }
            if (r5 == 0) goto L_0x006e
            if (r7 == 0) goto L_0x006e
            java.lang.Class r7 = r7.getSuperclass()     // Catch:{ IllegalArgumentException -> 0x006c }
            r4.zzg(r2, r3, r7)     // Catch:{ IllegalArgumentException -> 0x006c }
            goto L_0x0055
        L_0x0065:
            boolean r2 = r2.equals(r3)     // Catch:{ IllegalArgumentException -> 0x006c }
            r4.zzb = r2     // Catch:{ IllegalArgumentException -> 0x006c }
            goto L_0x006e
        L_0x006c:
            r4.zzb = r0
        L_0x006e:
            boolean r2 = r4.zzb
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzaho.zzf(java.lang.Object, java.lang.Object, boolean, java.lang.Class, boolean, java.lang.String[]):boolean");
    }

    private final void zzg(Object obj, Object obj2, Class cls) {
        Set zza2 = zza();
        zzahw zze = zze(obj, obj2);
        zzahv zzahv = (zzahv) zze;
        zzahv zzc2 = zzahv.zzc((zzaht) zzahv.zzb, (zzaht) zzahv.zza);
        if (zza2 == null || (!zza2.contains(zze) && !zza2.contains(zzc2))) {
            try {
                Set zza3 = zza();
                if (zza3 == null) {
                    zza3 = new HashSet();
                    zza.set(zza3);
                }
                zza3.add(zze(obj, obj2));
                Field[] declaredFields = cls.getDeclaredFields();
                AccessibleObject.setAccessible(declaredFields, true);
                for (int i = 0; i < declaredFields.length && this.zzb; i++) {
                    Field field = declaredFields[i];
                    if (!zzahl.zza(this.zzd, field.getName()) && !field.getName().contains("$") && !Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !field.isAnnotationPresent(zzahp.class)) {
                        zzd(zzahu.zza(field, obj), zzahu.zza(field, obj2));
                    }
                }
            } finally {
                zzh(obj, obj2);
            }
        }
    }

    private static void zzh(Object obj, Object obj2) {
        Set zza2 = zza();
        if (zza2 != null) {
            zza2.remove(zze(obj, obj2));
            if (zza2.isEmpty()) {
                zza.remove();
            }
        }
    }

    public final zzaho zzb(int i, int i2) {
        if (!this.zzb) {
            return this;
        }
        this.zzb = i == i2;
        return this;
    }

    public final zzaho zzc(long j, long j2) {
        if (!this.zzb) {
            return this;
        }
        this.zzb = j == j2;
        return this;
    }

    public final zzaho zzd(Object obj, Object obj2) {
        if (this.zzb && obj != obj2) {
            int i = 0;
            if (obj == null || obj2 == null) {
                this.zzb = false;
            } else if (!obj.getClass().isArray()) {
                this.zzb = obj.equals(obj2);
            } else if (obj.getClass() != obj2.getClass()) {
                this.zzb = false;
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                long[] jArr2 = (long[]) obj2;
                if (this.zzb && jArr != jArr2) {
                    if (jArr.length != jArr2.length) {
                        this.zzb = false;
                    } else {
                        while (i < jArr.length && this.zzb) {
                            zzc(jArr[i], jArr2[i]);
                            i++;
                        }
                    }
                }
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                int[] iArr2 = (int[]) obj2;
                if (this.zzb && iArr != iArr2) {
                    if (iArr.length != iArr2.length) {
                        this.zzb = false;
                    } else {
                        while (i < iArr.length && this.zzb) {
                            zzb(iArr[i], iArr2[i]);
                            i++;
                        }
                    }
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                short[] sArr2 = (short[]) obj2;
                if (this.zzb && sArr != sArr2) {
                    if (sArr.length != sArr2.length) {
                        this.zzb = false;
                    } else {
                        for (int i2 = 0; i2 < sArr.length && this.zzb; i2++) {
                            this.zzb = sArr[i2] == sArr2[i2];
                        }
                    }
                }
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                char[] cArr2 = (char[]) obj2;
                if (this.zzb && cArr != cArr2) {
                    if (cArr.length != cArr2.length) {
                        this.zzb = false;
                    } else {
                        for (int i3 = 0; i3 < cArr.length && this.zzb; i3++) {
                            this.zzb = cArr[i3] == cArr2[i3];
                        }
                    }
                }
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (this.zzb && bArr != bArr2) {
                    if (bArr.length != bArr2.length) {
                        this.zzb = false;
                    } else {
                        for (int i4 = 0; i4 < bArr.length && this.zzb; i4++) {
                            this.zzb = bArr[i4] == bArr2[i4];
                        }
                    }
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                double[] dArr2 = (double[]) obj2;
                if (this.zzb && dArr != dArr2) {
                    if (dArr.length != dArr2.length) {
                        this.zzb = false;
                    } else {
                        while (i < dArr.length && this.zzb) {
                            zzc(Double.doubleToLongBits(dArr[i]), Double.doubleToLongBits(dArr2[i]));
                            i++;
                        }
                    }
                }
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                float[] fArr2 = (float[]) obj2;
                if (this.zzb && fArr != fArr2) {
                    if (fArr.length != fArr2.length) {
                        this.zzb = false;
                    } else {
                        while (i < fArr.length && this.zzb) {
                            zzb(Float.floatToIntBits(fArr[i]), Float.floatToIntBits(fArr2[i]));
                            i++;
                        }
                    }
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                boolean[] zArr2 = (boolean[]) obj2;
                if (this.zzb && zArr != zArr2) {
                    if (zArr.length != zArr2.length) {
                        this.zzb = false;
                    } else {
                        for (int i5 = 0; i5 < zArr.length && this.zzb; i5++) {
                            this.zzb = zArr[i5] == zArr2[i5];
                        }
                    }
                }
            } else {
                Object[] objArr = (Object[]) obj;
                Object[] objArr2 = (Object[]) obj2;
                if (this.zzb && objArr != objArr2) {
                    if (objArr.length != objArr2.length) {
                        this.zzb = false;
                    } else {
                        while (i < objArr.length && this.zzb) {
                            zzd(objArr[i], objArr2[i]);
                            i++;
                        }
                    }
                }
            }
        }
        return this;
    }
}
