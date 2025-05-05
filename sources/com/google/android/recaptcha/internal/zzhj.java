package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhj implements zzgz {
    public static final zzhj zza = new zzhj();

    private zzhj() {
    }

    private final Object zzb(Object obj, Object obj2) throws zzcg, ArithmeticException {
        boolean z = obj instanceof Byte;
        if (z && (obj2 instanceof Byte)) {
            return Integer.valueOf(((Number) obj).intValue() % ((Number) obj2).intValue());
        }
        boolean z2 = obj instanceof Short;
        if (z2 && (obj2 instanceof Short)) {
            return Integer.valueOf(((Number) obj).intValue() % ((Number) obj2).intValue());
        }
        boolean z3 = obj instanceof Integer;
        if (z3 && (obj2 instanceof Integer)) {
            return Integer.valueOf(((Number) obj).intValue() % ((Number) obj2).intValue());
        }
        boolean z4 = obj instanceof Long;
        if (z4 && (obj2 instanceof Long)) {
            return Long.valueOf(((Number) obj).longValue() % ((Number) obj2).longValue());
        }
        boolean z5 = obj instanceof Float;
        if (z5 && (obj2 instanceof Float)) {
            return Float.valueOf(((Number) obj).floatValue() % ((Number) obj2).floatValue());
        }
        boolean z6 = obj instanceof Double;
        if (z6 && (obj2 instanceof Double)) {
            return Double.valueOf(((Number) obj).doubleValue() % ((Number) obj2).doubleValue());
        }
        int i = 0;
        if (obj instanceof String) {
            if (obj2 instanceof Byte) {
                byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
                int length = bytes.length;
                Collection arrayList = new ArrayList(length);
                while (i < length) {
                    arrayList.add(Byte.valueOf((byte) (bytes[i] % ((Number) obj2).intValue())));
                    i++;
                }
                return new String(CollectionsKt.toByteArray((List) arrayList), Charsets.UTF_8);
            } else if (obj2 instanceof Integer) {
                char[] charArray = ((String) obj).toCharArray();
                int length2 = charArray.length;
                Collection arrayList2 = new ArrayList(length2);
                while (i < length2) {
                    arrayList2.add(Integer.valueOf(charArray[i] % ((Number) obj2).intValue()));
                    i++;
                }
                return (Serializable) CollectionsKt.toIntArray((List) arrayList2);
            }
        }
        if (z && (obj2 instanceof byte[])) {
            Collection arrayList3 = new ArrayList(r0);
            for (byte intValue : (byte[]) obj2) {
                arrayList3.add(Integer.valueOf(intValue % ((Number) obj).intValue()));
            }
            return (Serializable) ((List) arrayList3).toArray(new Integer[0]);
        } else if (z2 && (obj2 instanceof short[])) {
            Collection arrayList4 = new ArrayList(r0);
            for (short intValue2 : (short[]) obj2) {
                arrayList4.add(Integer.valueOf(intValue2 % ((Number) obj).intValue()));
            }
            return (Serializable) ((List) arrayList4).toArray(new Integer[0]);
        } else if (z3 && (obj2 instanceof int[])) {
            Collection arrayList5 = new ArrayList(r0);
            for (int intValue3 : (int[]) obj2) {
                arrayList5.add(Integer.valueOf(intValue3 % ((Number) obj).intValue()));
            }
            return (Serializable) ((List) arrayList5).toArray(new Integer[0]);
        } else if (z4 && (obj2 instanceof long[])) {
            Collection arrayList6 = new ArrayList(r0);
            for (long longValue : (long[]) obj2) {
                arrayList6.add(Long.valueOf(longValue % ((Number) obj).longValue()));
            }
            return (Serializable) ((List) arrayList6).toArray(new Long[0]);
        } else if (z5 && (obj2 instanceof float[])) {
            Collection arrayList7 = new ArrayList(r0);
            for (float floatValue : (float[]) obj2) {
                arrayList7.add(Float.valueOf(floatValue % ((Number) obj).floatValue()));
            }
            return (Serializable) ((List) arrayList7).toArray(new Float[0]);
        } else if (!z6 || !(obj2 instanceof double[])) {
            boolean z7 = obj instanceof byte[];
            if (!z7 || !(obj2 instanceof Byte)) {
                boolean z8 = obj instanceof short[];
                if (!z8 || !(obj2 instanceof Short)) {
                    boolean z9 = obj instanceof int[];
                    if (!z9 || !(obj2 instanceof Integer)) {
                        boolean z10 = obj instanceof long[];
                        if (!z10 || !(obj2 instanceof Long)) {
                            boolean z11 = obj instanceof float[];
                            if (!z11 || !(obj2 instanceof Float)) {
                                boolean z12 = obj instanceof double[];
                                if (z12 && (obj2 instanceof Double)) {
                                    Collection arrayList8 = new ArrayList(r0);
                                    for (double doubleValue : (double[]) obj) {
                                        arrayList8.add(Double.valueOf(doubleValue % ((Number) obj2).doubleValue()));
                                    }
                                    return (Serializable) ((List) arrayList8).toArray(new Double[0]);
                                } else if (z7 && (obj2 instanceof byte[])) {
                                    byte[] bArr = (byte[]) obj;
                                    int length3 = bArr.length;
                                    byte[] bArr2 = (byte[]) obj2;
                                    zzgy.zza(this, length3, bArr2.length);
                                    Iterable until = RangesKt.until(0, length3);
                                    Collection arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                                    Iterator it = until.iterator();
                                    while (it.hasNext()) {
                                        int nextInt = ((IntIterator) it).nextInt();
                                        arrayList9.add(Integer.valueOf(bArr[nextInt] % bArr2[nextInt]));
                                    }
                                    return (Serializable) ((List) arrayList9).toArray(new Integer[0]);
                                } else if (z8 && (obj2 instanceof short[])) {
                                    short[] sArr = (short[]) obj;
                                    int length4 = sArr.length;
                                    short[] sArr2 = (short[]) obj2;
                                    zzgy.zza(this, length4, sArr2.length);
                                    Iterable until2 = RangesKt.until(0, length4);
                                    Collection arrayList10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until2, 10));
                                    Iterator it2 = until2.iterator();
                                    while (it2.hasNext()) {
                                        int nextInt2 = ((IntIterator) it2).nextInt();
                                        arrayList10.add(Integer.valueOf(sArr[nextInt2] % sArr2[nextInt2]));
                                    }
                                    return (Serializable) ((List) arrayList10).toArray(new Integer[0]);
                                } else if (z9 && (obj2 instanceof int[])) {
                                    int[] iArr = (int[]) obj;
                                    int length5 = iArr.length;
                                    int[] iArr2 = (int[]) obj2;
                                    zzgy.zza(this, length5, iArr2.length);
                                    Iterable until3 = RangesKt.until(0, length5);
                                    Collection arrayList11 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until3, 10));
                                    Iterator it3 = until3.iterator();
                                    while (it3.hasNext()) {
                                        int nextInt3 = ((IntIterator) it3).nextInt();
                                        arrayList11.add(Integer.valueOf(iArr[nextInt3] % iArr2[nextInt3]));
                                    }
                                    return (Serializable) ((List) arrayList11).toArray(new Integer[0]);
                                } else if (z10 && (obj2 instanceof long[])) {
                                    long[] jArr = (long[]) obj;
                                    int length6 = jArr.length;
                                    long[] jArr2 = (long[]) obj2;
                                    zzgy.zza(this, length6, jArr2.length);
                                    Iterable until4 = RangesKt.until(0, length6);
                                    Collection arrayList12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until4, 10));
                                    Iterator it4 = until4.iterator();
                                    while (it4.hasNext()) {
                                        int nextInt4 = ((IntIterator) it4).nextInt();
                                        arrayList12.add(Long.valueOf(jArr[nextInt4] % jArr2[nextInt4]));
                                    }
                                    return (Serializable) ((List) arrayList12).toArray(new Long[0]);
                                } else if (z11 && (obj2 instanceof float[])) {
                                    float[] fArr = (float[]) obj;
                                    int length7 = fArr.length;
                                    float[] fArr2 = (float[]) obj2;
                                    zzgy.zza(this, length7, fArr2.length);
                                    Iterable until5 = RangesKt.until(0, length7);
                                    Collection arrayList13 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until5, 10));
                                    Iterator it5 = until5.iterator();
                                    while (it5.hasNext()) {
                                        int nextInt5 = ((IntIterator) it5).nextInt();
                                        arrayList13.add(Float.valueOf(fArr[nextInt5] % fArr2[nextInt5]));
                                    }
                                    return (Serializable) ((List) arrayList13).toArray(new Float[0]);
                                } else if (!z12 || !(obj2 instanceof double[])) {
                                    throw new zzcg(4, 5, (Throwable) null);
                                } else {
                                    double[] dArr = (double[]) obj;
                                    int length8 = dArr.length;
                                    double[] dArr2 = (double[]) obj2;
                                    zzgy.zza(this, length8, dArr2.length);
                                    Iterable until6 = RangesKt.until(0, length8);
                                    Collection arrayList14 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until6, 10));
                                    Iterator it6 = until6.iterator();
                                    while (it6.hasNext()) {
                                        int nextInt6 = ((IntIterator) it6).nextInt();
                                        arrayList14.add(Double.valueOf(dArr[nextInt6] % dArr2[nextInt6]));
                                    }
                                    return (Serializable) ((List) arrayList14).toArray(new Double[0]);
                                }
                            } else {
                                Collection arrayList15 = new ArrayList(r0);
                                for (float floatValue2 : (float[]) obj) {
                                    arrayList15.add(Float.valueOf(floatValue2 % ((Number) obj2).floatValue()));
                                }
                                return (Serializable) ((List) arrayList15).toArray(new Float[0]);
                            }
                        } else {
                            Collection arrayList16 = new ArrayList(r0);
                            for (long longValue2 : (long[]) obj) {
                                arrayList16.add(Long.valueOf(longValue2 % ((Number) obj2).longValue()));
                            }
                            return (Serializable) ((List) arrayList16).toArray(new Long[0]);
                        }
                    } else {
                        int[] iArr3 = (int[]) obj;
                        int length9 = iArr3.length;
                        Collection arrayList17 = new ArrayList(length9);
                        while (i < length9) {
                            arrayList17.add(Integer.valueOf(iArr3[i] % ((Number) obj2).intValue()));
                            i++;
                        }
                        return (Serializable) CollectionsKt.toIntArray((List) arrayList17);
                    }
                } else {
                    Collection arrayList18 = new ArrayList(r0);
                    for (short intValue4 : (short[]) obj) {
                        arrayList18.add(Integer.valueOf(intValue4 % ((Number) obj2).intValue()));
                    }
                    return (Serializable) ((List) arrayList18).toArray(new Integer[0]);
                }
            } else {
                Collection arrayList19 = new ArrayList(r0);
                for (byte intValue5 : (byte[]) obj) {
                    arrayList19.add(Integer.valueOf(intValue5 % ((Number) obj2).intValue()));
                }
                return (Serializable) ((List) arrayList19).toArray(new Integer[0]);
            }
        } else {
            Collection arrayList20 = new ArrayList(r0);
            for (double doubleValue2 : (double[]) obj2) {
                arrayList20.add(Double.valueOf(doubleValue2 % ((Number) obj).doubleValue()));
            }
            return (Serializable) ((List) arrayList20).toArray(new Double[0]);
        }
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        if (zzugArr.length == 2) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Object)) {
                zza2 = null;
            }
            if (zza2 != null) {
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof Object)) {
                    zza3 = null;
                }
                if (zza3 != null) {
                    try {
                        zzgf.zzc().zze(i, zzb(zza2, zza3));
                    } catch (ArithmeticException e) {
                        throw new zzcg(4, 6, e);
                    }
                } else {
                    throw new zzcg(4, 5, (Throwable) null);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
