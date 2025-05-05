package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzha implements zzgz {
    public static final zzha zza = new zzha();

    private zzha() {
    }

    private static final List zzc(Object obj) {
        if (obj instanceof byte[]) {
            return ArraysKt.toList((byte[]) obj);
        }
        if (obj instanceof short[]) {
            return ArraysKt.toList((short[]) obj);
        }
        if (obj instanceof int[]) {
            return ArraysKt.toList((int[]) obj);
        }
        if (obj instanceof long[]) {
            return ArraysKt.toList((long[]) obj);
        }
        if (obj instanceof float[]) {
            return ArraysKt.toList((float[]) obj);
        }
        if (!(obj instanceof double[])) {
            return null;
        }
        return ArraysKt.toList((double[]) obj);
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
        List zzc = zzc(obj);
        List zzc2 = zzc(obj2);
        if (obj instanceof Number) {
            if (obj2 instanceof Number) {
                return Double.valueOf(Math.pow(((Number) obj).doubleValue(), ((Number) obj2).doubleValue()));
            }
            if (zzc2 != null) {
                Iterable<Number> iterable = zzc2;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Number doubleValue : iterable) {
                    arrayList.add(Double.valueOf(Math.pow(doubleValue.doubleValue(), ((Number) obj).doubleValue())));
                }
                return (Serializable) ((List) arrayList).toArray(new Double[0]);
            }
        }
        if (zzc != null && (obj2 instanceof Number)) {
            Iterable<Number> iterable2 = zzc;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (Number doubleValue2 : iterable2) {
                arrayList2.add(Double.valueOf(Math.pow(doubleValue2.doubleValue(), ((Number) obj2).doubleValue())));
            }
            return (Serializable) ((List) arrayList2).toArray(new Double[0]);
        } else if (zzc == null || zzc2 == null) {
            throw new zzcg(4, 5, (Throwable) null);
        } else {
            zzgy.zza(this, zzc.size(), zzc2.size());
            int size = zzc.size();
            Double[] dArr = new Double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = Double.valueOf(Math.pow(((Number) zzc.get(i)).doubleValue(), ((Number) zzc2.get(i)).doubleValue()));
            }
            return (Serializable) dArr;
        }
    }
}
