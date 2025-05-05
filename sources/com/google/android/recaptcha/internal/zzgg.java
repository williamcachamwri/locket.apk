package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgg {
    private final Map zza = new LinkedHashMap();
    private final Set zzb = new LinkedHashSet();

    private final List zzh(List list) {
        Iterable<zzug> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (zzug zza2 : iterable) {
            arrayList.add(zza(zza2));
        }
        return (List) arrayList;
    }

    public final Object zza(zzug zzug) throws zzcg {
        int zzS = zzug.zzS();
        int i = zzS - 1;
        if (zzS != 0) {
            switch (i) {
                case 0:
                    return this.zza.get(Integer.valueOf(zzug.zzi()));
                case 1:
                    return Boolean.valueOf(zzug.zzQ());
                case 2:
                    byte[] zzo = zzug.zzM().zzo();
                    if (zzo.length == 1) {
                        return Byte.valueOf(zzo[0]);
                    }
                    throw new zzcg(4, 6, (Throwable) null);
                case 3:
                    String zzO = zzug.zzO();
                    if (zzO.length() == 1) {
                        return Character.valueOf(zzO.charAt(0));
                    }
                    throw new zzcg(4, 6, (Throwable) null);
                case 4:
                    int zzj = zzug.zzj();
                    if (zzj >= -32768 && zzj <= 32767) {
                        return Short.valueOf((short) zzj);
                    }
                    throw new zzcg(4, 6, (Throwable) null);
                case 5:
                    return Integer.valueOf(zzug.zzk());
                case 6:
                case 8:
                    throw new zzcg(4, 6, (Throwable) null);
                case 7:
                    return Long.valueOf(zzug.zzl());
                case 9:
                    return Float.valueOf(zzug.zzg());
                case 10:
                    return Double.valueOf(zzug.zzf());
                case 11:
                    return zzug.zzP();
                case 12:
                    return null;
                default:
                    throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw null;
        }
    }

    public final Object zzb(int i) {
        return this.zza.remove(Integer.valueOf(i));
    }

    public final void zzc() {
        this.zza.clear();
    }

    public final void zzd(int i, Object obj) {
        zze(173, obj);
        this.zzb.add(173);
    }

    public final void zze(int i, Object obj) {
        this.zza.put(Integer.valueOf(i), obj);
    }

    public final Class[] zzf(List list) {
        Iterable<Object> zzh = zzh(list);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(zzh, 10));
        for (Object zza2 : zzh) {
            arrayList.add(zzge.zza(zza2));
        }
        return (Class[]) ((List) arrayList).toArray(new Class[0]);
    }

    public final Object[] zzg(List list) {
        return zzh(list).toArray(new Object[0]);
    }
}
