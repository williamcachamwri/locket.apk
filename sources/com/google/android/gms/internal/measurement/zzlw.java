package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzlw {
    private static final zzmk<?, ?> zza = new zzmm();

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjc.zzb(i, true);
    }

    static int zza(List<?> list) {
        return list.size();
    }

    static int zza(int i, List<zzik> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = size * zzjc.zzi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzi += zzjc.zzb(list.get(i2));
        }
        return zzi;
    }

    static int zzb(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzjc.zzi(i));
    }

    static int zzb(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzd(zzjw.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzjc.zzd(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzc(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjc.zzf(i, 0);
    }

    static int zzc(List<?> list) {
        return list.size() << 2;
    }

    static int zzd(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzjc.zzc(i, 0);
    }

    static int zzd(List<?> list) {
        return list.size() << 3;
    }

    static int zza(int i, List<zzlc> list, zzlu<?> zzlu) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzjc.zzb(i, list.get(i3), (zzlu) zzlu);
        }
        return i2;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzjc.zzi(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzf(zzjw.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzjc.zzf(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzf(list) + (list.size() * zzjc.zzi(i));
    }

    static int zzf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzd(zzkn.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzjc.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zza(int i, Object obj, zzlu<?> zzlu) {
        if (obj instanceof zzkk) {
            return zzjc.zzb(i, (zzkk) obj);
        }
        return zzjc.zzc(i, (zzlc) obj, zzlu);
    }

    static int zzb(int i, List<?> list, zzlu<?> zzlu) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzjc.zzi(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzkk) {
                i2 = zzjc.zza((zzkk) obj);
            } else {
                i2 = zzjc.zza((zzlc) obj, (zzlu) zzlu);
            }
            zzi += i2;
        }
        return zzi;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzjc.zzi(i));
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzh(zzjw.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzjc.zzh(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzjc.zzi(i));
    }

    static int zzh(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzf(zzkn.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzjc.zzf(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzb(int i, List<?> list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzi = zzjc.zzi(i) * size;
        if (list instanceof zzkj) {
            zzkj zzkj = (zzkj) list;
            while (i4 < size) {
                Object zza2 = zzkj.zza(i4);
                if (zza2 instanceof zzik) {
                    i3 = zzjc.zzb((zzik) zza2);
                } else {
                    i3 = zzjc.zzb((String) zza2);
                }
                zzi += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzik) {
                    i2 = zzjc.zzb((zzik) obj);
                } else {
                    i2 = zzjc.zzb((String) obj);
                }
                zzi += i2;
                i4++;
            }
        }
        return zzi;
    }

    static int zzi(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzjc.zzi(i));
    }

    static int zzi(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzj(zzjw.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzjc.zzj(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzj(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzjc.zzi(i));
    }

    static int zzj(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            i = 0;
            while (i2 < size) {
                i += zzjc.zzg(zzkn.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzjc.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzmk<?, ?> zza() {
        return zza;
    }

    static <UT, UB> UB zza(Object obj, int i, List<Integer> list, zzjx zzjx, UB ub, zzmk<UT, UB> zzmk) {
        if (zzjx == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzjx.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zza(obj, i, intValue, ub, zzmk);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzjx.zza(intValue2)) {
                    ub = zza(obj, i, intValue2, ub, zzmk);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(Object obj, int i, int i2, UB ub, zzmk<UT, UB> zzmk) {
        if (ub == null) {
            ub = zzmk.zzc(obj);
        }
        zzmk.zzb(ub, i, (long) i2);
        return ub;
    }

    static <T, FT extends zzjo<FT>> void zza(zzji<FT> zzji, T t, T t2) {
        zzjm<FT> zza2 = zzji.zza((Object) t2);
        if (!zza2.zza.isEmpty()) {
            zzji.zzb(t).zza(zza2);
        }
    }

    static <T> void zza(zzkv zzkv, T t, T t2, long j) {
        zzml.zza((Object) t, j, zzkv.zza(zzml.zze(t, j), zzml.zze(t2, j)));
    }

    static <T, UT, UB> void zza(zzmk<UT, UB> zzmk, T t, T t2) {
        zzmk.zzc(t, zzmk.zza(zzmk.zzd(t), zzmk.zzd(t2)));
    }

    public static void zza(Class<?> cls) {
        zzjt.class.isAssignableFrom(cls);
    }

    public static void zza(int i, List<Boolean> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zza(i, list, z);
        }
    }

    public static void zza(int i, List<zzik> list, zznb zznb) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zza(i, list);
        }
    }

    public static void zzb(int i, List<Double> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzb(i, list, z);
        }
    }

    public static void zzc(int i, List<Integer> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Integer> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zze(i, list, z);
        }
    }

    public static void zzf(int i, List<Float> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzf(i, list, z);
        }
    }

    public static void zza(int i, List<?> list, zznb zznb, zzlu<?> zzlu) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zza(i, list, (zzlu) zzlu);
        }
    }

    public static void zzg(int i, List<Integer> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzg(i, list, z);
        }
    }

    public static void zzh(int i, List<Long> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzh(i, list, z);
        }
    }

    public static void zzb(int i, List<?> list, zznb zznb, zzlu<?> zzlu) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzb(i, list, (zzlu) zzlu);
        }
    }

    public static void zzi(int i, List<Integer> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzi(i, list, z);
        }
    }

    public static void zzj(int i, List<Long> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzj(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzk(i, list, z);
        }
    }

    public static void zzl(int i, List<Long> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzl(i, list, z);
        }
    }

    public static void zzb(int i, List<String> list, zznb zznb) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzb(i, list);
        }
    }

    public static void zzm(int i, List<Integer> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzm(i, list, z);
        }
    }

    public static void zzn(int i, List<Long> list, zznb zznb, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zznb.zzn(i, list, z);
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
