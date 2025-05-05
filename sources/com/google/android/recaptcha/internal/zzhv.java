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
public final class zzhv implements zzgz {
    public static final zzhv zza = new zzhv();

    private zzhv() {
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
                    zzgf.zzc().zze(i, zzb(zza2, zza3));
                    return;
                }
                throw new zzcg(4, 5, (Throwable) null);
            }
            throw new zzcg(4, 5, (Throwable) null);
        }
        throw new zzcg(4, 3, (Throwable) null);
    }

    public final Object zzb(Object obj, Object obj2) throws zzcg {
        boolean z = obj instanceof Byte;
        if (z && (obj2 instanceof Byte)) {
            return Byte.valueOf((byte) (((Number) obj).byteValue() ^ ((Number) obj2).byteValue()));
        }
        boolean z2 = obj instanceof Short;
        if (z2 && (obj2 instanceof Short)) {
            return Short.valueOf((short) (((Number) obj).shortValue() ^ ((Number) obj2).shortValue()));
        }
        boolean z3 = obj instanceof Integer;
        if (z3 && (obj2 instanceof Integer)) {
            return Integer.valueOf(((Number) obj).intValue() ^ ((Number) obj2).intValue());
        }
        boolean z4 = obj instanceof Long;
        if (!z4 || !(obj2 instanceof Long)) {
            int i = 0;
            if (obj instanceof String) {
                if (obj2 instanceof Byte) {
                    byte[] bytes = ((String) obj).getBytes(Charsets.UTF_8);
                    int length = bytes.length;
                    Collection arrayList = new ArrayList(length);
                    while (i < length) {
                        arrayList.add(Byte.valueOf((byte) (bytes[i] ^ ((Number) obj2).byteValue())));
                        i++;
                    }
                    return (Serializable) CollectionsKt.toByteArray((List) arrayList);
                } else if (obj2 instanceof Integer) {
                    char[] charArray = ((String) obj).toCharArray();
                    int length2 = charArray.length;
                    Collection arrayList2 = new ArrayList(length2);
                    while (i < length2) {
                        arrayList2.add(Integer.valueOf(charArray[i] ^ ((Number) obj2).intValue()));
                        i++;
                    }
                    return (Serializable) CollectionsKt.toIntArray((List) arrayList2);
                }
            }
            if (z && (obj2 instanceof byte[])) {
                Collection arrayList3 = new ArrayList(r0);
                for (byte byteValue : (byte[]) obj2) {
                    arrayList3.add(Byte.valueOf((byte) (byteValue ^ ((Number) obj).byteValue())));
                }
                return (Serializable) ((List) arrayList3).toArray(new Byte[0]);
            } else if (z2 && (obj2 instanceof short[])) {
                Collection arrayList4 = new ArrayList(r0);
                for (short shortValue : (short[]) obj2) {
                    arrayList4.add(Short.valueOf((short) (shortValue ^ ((Number) obj).shortValue())));
                }
                return (Serializable) ((List) arrayList4).toArray(new Short[0]);
            } else if (z3 && (obj2 instanceof int[])) {
                Collection arrayList5 = new ArrayList(r0);
                for (int intValue : (int[]) obj2) {
                    arrayList5.add(Integer.valueOf(intValue ^ ((Number) obj).intValue()));
                }
                return (Serializable) ((List) arrayList5).toArray(new Integer[0]);
            } else if (!z4 || !(obj2 instanceof long[])) {
                boolean z5 = obj instanceof byte[];
                if (!z5 || !(obj2 instanceof Byte)) {
                    boolean z6 = obj instanceof short[];
                    if (!z6 || !(obj2 instanceof Short)) {
                        boolean z7 = obj instanceof int[];
                        if (!z7 || !(obj2 instanceof Integer)) {
                            boolean z8 = obj instanceof long[];
                            if (z8 && (obj2 instanceof Long)) {
                                Collection arrayList6 = new ArrayList(r0);
                                for (long longValue : (long[]) obj) {
                                    arrayList6.add(Long.valueOf(longValue ^ ((Number) obj2).longValue()));
                                }
                                return (Serializable) ((List) arrayList6).toArray(new Long[0]);
                            } else if (z5 && (obj2 instanceof byte[])) {
                                byte[] bArr = (byte[]) obj;
                                int length3 = bArr.length;
                                byte[] bArr2 = (byte[]) obj2;
                                zzgy.zza(this, length3, bArr2.length);
                                Iterable until = RangesKt.until(0, length3);
                                Collection arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                                Iterator it = until.iterator();
                                while (it.hasNext()) {
                                    int nextInt = ((IntIterator) it).nextInt();
                                    arrayList7.add(Byte.valueOf((byte) (bArr2[nextInt] ^ bArr[nextInt])));
                                }
                                return (Serializable) ((List) arrayList7).toArray(new Byte[0]);
                            } else if (z6 && (obj2 instanceof short[])) {
                                short[] sArr = (short[]) obj;
                                int length4 = sArr.length;
                                short[] sArr2 = (short[]) obj2;
                                zzgy.zza(this, length4, sArr2.length);
                                Iterable until2 = RangesKt.until(0, length4);
                                Collection arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until2, 10));
                                Iterator it2 = until2.iterator();
                                while (it2.hasNext()) {
                                    int nextInt2 = ((IntIterator) it2).nextInt();
                                    arrayList8.add(Short.valueOf((short) (sArr2[nextInt2] ^ sArr[nextInt2])));
                                }
                                return (Serializable) ((List) arrayList8).toArray(new Short[0]);
                            } else if (z7 && (obj2 instanceof int[])) {
                                int[] iArr = (int[]) obj;
                                int length5 = iArr.length;
                                int[] iArr2 = (int[]) obj2;
                                zzgy.zza(this, length5, iArr2.length);
                                Iterable until3 = RangesKt.until(0, length5);
                                Collection arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until3, 10));
                                Iterator it3 = until3.iterator();
                                while (it3.hasNext()) {
                                    int nextInt3 = ((IntIterator) it3).nextInt();
                                    arrayList9.add(Integer.valueOf(iArr2[nextInt3] ^ iArr[nextInt3]));
                                }
                                return (Serializable) ((List) arrayList9).toArray(new Integer[0]);
                            } else if (!z8 || !(obj2 instanceof long[])) {
                                throw new zzcg(4, 5, (Throwable) null);
                            } else {
                                long[] jArr = (long[]) obj;
                                int length6 = jArr.length;
                                long[] jArr2 = (long[]) obj2;
                                zzgy.zza(this, length6, jArr2.length);
                                Iterable until4 = RangesKt.until(0, length6);
                                Collection arrayList10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(until4, 10));
                                Iterator it4 = until4.iterator();
                                while (it4.hasNext()) {
                                    int nextInt4 = ((IntIterator) it4).nextInt();
                                    arrayList10.add(Long.valueOf(jArr[nextInt4] ^ jArr2[nextInt4]));
                                }
                                return (Serializable) ((List) arrayList10).toArray(new Long[0]);
                            }
                        } else {
                            Collection arrayList11 = new ArrayList(r0);
                            for (int intValue2 : (int[]) obj) {
                                arrayList11.add(Integer.valueOf(intValue2 ^ ((Number) obj2).intValue()));
                            }
                            return (Serializable) ((List) arrayList11).toArray(new Integer[0]);
                        }
                    } else {
                        Collection arrayList12 = new ArrayList(r0);
                        for (short shortValue2 : (short[]) obj) {
                            arrayList12.add(Short.valueOf((short) (shortValue2 ^ ((Number) obj2).shortValue())));
                        }
                        return (Serializable) ((List) arrayList12).toArray(new Short[0]);
                    }
                } else {
                    Collection arrayList13 = new ArrayList(r0);
                    for (byte byteValue2 : (byte[]) obj) {
                        arrayList13.add(Byte.valueOf((byte) (byteValue2 ^ ((Number) obj2).byteValue())));
                    }
                    return (Serializable) ((List) arrayList13).toArray(new Byte[0]);
                }
            } else {
                Collection arrayList14 = new ArrayList(r0);
                for (long longValue2 : (long[]) obj2) {
                    arrayList14.add(Long.valueOf(longValue2 ^ ((Number) obj).longValue()));
                }
                return (Serializable) ((List) arrayList14).toArray(new Long[0]);
            }
        } else {
            return Long.valueOf(((Number) obj2).longValue() ^ ((Number) obj).longValue());
        }
    }
}
