package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzahr {
    private static final ThreadLocal zza = new ThreadLocal();
    private int zzb = 17;

    public static int zza(Object obj, String... strArr) {
        Objects.requireNonNull(obj, "object");
        zzahr zzahr = new zzahr();
        Class cls = obj.getClass();
        zze(obj, cls, zzahr, false, strArr);
        while (cls.getSuperclass() != null && cls != null) {
            cls = cls.getSuperclass();
            zze(obj, cls, zzahr, false, strArr);
        }
        return zzahr.zzb;
    }

    static Set zzb() {
        return (Set) zza.get();
    }

    private static void zze(Object obj, Class cls, zzahr zzahr, boolean z, String[] strArr) {
        Set zzb2 = zzb();
        if (zzb2 == null || !zzb2.contains(new zzaht(obj))) {
            try {
                Set zzb3 = zzb();
                if (zzb3 == null) {
                    zzb3 = new HashSet();
                    zza.set(zzb3);
                }
                zzb3.add(new zzaht(obj));
                Field[] declaredFields = cls.getDeclaredFields();
                Arrays.sort(declaredFields, Comparator.comparing(new zzahq()));
                Field[] fieldArr = declaredFields;
                AccessibleObject.setAccessible(fieldArr, true);
                for (Field field : fieldArr) {
                    if (!zzahl.zza(strArr, field.getName()) && !field.getName().contains("$") && !Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !field.isAnnotationPresent(zzahs.class)) {
                        zzahr.zzd(zzahu.zza(field, obj));
                    }
                }
            } finally {
                zzf(obj);
            }
        }
    }

    private static void zzf(Object obj) {
        Set zzb2 = zzb();
        if (zzb2 != null) {
            zzb2.remove(new zzaht(obj));
            if (zzb2.isEmpty()) {
                zza.remove();
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzahr) && this.zzb == ((zzahr) obj).zzb;
    }

    public final int hashCode() {
        return this.zzb;
    }

    public final zzahr zzc(long j) {
        this.zzb = (this.zzb * 37) + ((int) (j ^ (j >> 32)));
        return this;
    }

    public final zzahr zzd(Object obj) {
        if (obj == null) {
            this.zzb *= 37;
        } else if (zzahm.zza(obj)) {
            int i = 0;
            if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                int length = jArr.length;
                while (i < length) {
                    zzc(jArr[i]);
                    i++;
                }
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                int length2 = iArr.length;
                while (i < length2) {
                    this.zzb = (this.zzb * 37) + iArr[i];
                    i++;
                }
            } else if (obj instanceof short[]) {
                short[] sArr = (short[]) obj;
                int length3 = sArr.length;
                while (i < length3) {
                    this.zzb = (this.zzb * 37) + sArr[i];
                    i++;
                }
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                int length4 = cArr.length;
                while (i < length4) {
                    this.zzb = (this.zzb * 37) + cArr[i];
                    i++;
                }
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int length5 = bArr.length;
                while (i < length5) {
                    this.zzb = (this.zzb * 37) + bArr[i];
                    i++;
                }
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                int length6 = dArr.length;
                while (i < length6) {
                    zzc(Double.doubleToLongBits(dArr[i]));
                    i++;
                }
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                int length7 = fArr.length;
                while (i < length7) {
                    this.zzb = (this.zzb * 37) + Float.floatToIntBits(fArr[i]);
                    i++;
                }
            } else if (obj instanceof boolean[]) {
                boolean[] zArr = (boolean[]) obj;
                int length8 = zArr.length;
                while (i < length8) {
                    this.zzb = (this.zzb * 37) + (zArr[i] ^ true ? 1 : 0);
                    i++;
                }
            } else {
                Object[] objArr = (Object[]) obj;
                int length9 = objArr.length;
                while (i < length9) {
                    zzd(objArr[i]);
                    i++;
                }
            }
        } else {
            this.zzb = (this.zzb * 37) + obj.hashCode();
        }
        return this;
    }
}
