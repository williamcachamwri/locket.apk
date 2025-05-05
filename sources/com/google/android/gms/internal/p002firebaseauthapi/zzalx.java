package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzalx {
    private static final zzamo<?, ?> zza = new zzamq();

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzajg.zza(i, true);
    }

    static int zza(List<?> list) {
        return list.size();
    }

    static int zza(int i, List<zzaip> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = size * zzajg.zzi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzi += zzajg.zza(list.get(i2));
        }
        return zzi;
    }

    static int zzb(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzajg.zzi(i));
    }

    static int zzb(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            i = 0;
            while (i2 < size) {
                i += zzajg.zzc(zzajz.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzajg.zzc(list.get(i2).intValue());
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
        return size * zzajg.zzc(i, 0);
    }

    static int zzc(List<?> list) {
        return list.size() << 2;
    }

    static int zzd(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzajg.zza(i, 0);
    }

    static int zzd(List<?> list) {
        return list.size() << 3;
    }

    static int zza(int i, List<zzalc> list, zzalv<?> zzalv) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzajg.zza(i, list.get(i3), zzalv);
        }
        return i2;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzajg.zzi(i));
    }

    static int zze(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            i = 0;
            while (i2 < size) {
                i += zzajg.zze(zzajz.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzajg.zze(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzf(list) + (list.size() * zzajg.zzi(i));
    }

    static int zzf(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            i = 0;
            while (i2 < size) {
                i += zzajg.zzd(zzakr.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzajg.zzd(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zza(int i, Object obj, zzalv<?> zzalv) {
        if (obj instanceof zzakk) {
            return zzajg.zzb(i, (zzakk) obj);
        }
        return zzajg.zzb(i, (zzalc) obj, (zzalv) zzalv);
    }

    static int zzb(int i, List<?> list, zzalv<?> zzalv) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzi = zzajg.zzi(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzakk) {
                i2 = zzajg.zza((zzakk) obj);
            } else {
                i2 = zzajg.zza((zzalc) obj, (zzalv) zzalv);
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
        return zzg(list) + (size * zzajg.zzi(i));
    }

    static int zzg(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            i = 0;
            while (i2 < size) {
                i += zzajg.zzh(zzajz.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzajg.zzh(list.get(i2).intValue());
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
        return zzh(list) + (size * zzajg.zzi(i));
    }

    static int zzh(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            i = 0;
            while (i2 < size) {
                i += zzajg.zzf(zzakr.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzajg.zzf(list.get(i2).longValue());
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
        int zzi = zzajg.zzi(i) * size;
        if (list instanceof zzakn) {
            zzakn zzakn = (zzakn) list;
            while (i4 < size) {
                Object zza2 = zzakn.zza(i4);
                if (zza2 instanceof zzaip) {
                    i3 = zzajg.zza((zzaip) zza2);
                } else {
                    i3 = zzajg.zza((String) zza2);
                }
                zzi += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzaip) {
                    i2 = zzajg.zza((zzaip) obj);
                } else {
                    i2 = zzajg.zza((String) obj);
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
        return zzi(list) + (size * zzajg.zzi(i));
    }

    static int zzi(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            i = 0;
            while (i2 < size) {
                i += zzajg.zzj(zzajz.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzajg.zzj(list.get(i2).intValue());
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
        return zzj(list) + (size * zzajg.zzi(i));
    }

    static int zzj(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            i = 0;
            while (i2 < size) {
                i += zzajg.zzg(zzakr.zzb(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzajg.zzg(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzamo<?, ?> zza() {
        return zza;
    }

    static <UT, UB> UB zza(Object obj, int i, List<Integer> list, zzakd zzakd, UB ub, zzamo<UT, UB> zzamo) {
        if (zzakd == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = list.get(i3).intValue();
                if (zzakd.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    ub = zza(obj, i, intValue, ub, zzamo);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzakd.zza(intValue2)) {
                    ub = zza(obj, i, intValue2, ub, zzamo);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(Object obj, int i, int i2, UB ub, zzamo<UT, UB> zzamo) {
        if (ub == null) {
            ub = zzamo.zzc(obj);
        }
        zzamo.zzb(ub, i, (long) i2);
        return ub;
    }

    static <T, FT extends zzajt<FT>> void zza(zzajm<FT> zzajm, T t, T t2) {
        zzajr<FT> zza2 = zzajm.zza((Object) t2);
        if (!zza2.zza.isEmpty()) {
            zzajm.zzb(t).zza(zza2);
        }
    }

    static <T> void zza(zzakz zzakz, T t, T t2, long j) {
        zzamp.zza((Object) t, j, zzakz.zza(zzamp.zze(t, j), zzamp.zze(t2, j)));
    }

    static <T, UT, UB> void zza(zzamo<UT, UB> zzamo, T t, T t2) {
        zzamo.zzc(t, zzamo.zza(zzamo.zzd(t), zzamo.zzd(t2)));
    }

    public static void zza(Class<?> cls) {
        zzajy.class.isAssignableFrom(cls);
    }

    public static void zza(int i, List<Boolean> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zza(i, list, z);
        }
    }

    public static void zza(int i, List<zzaip> list, zzanf zzanf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zza(i, list);
        }
    }

    public static void zzb(int i, List<Double> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzb(i, list, z);
        }
    }

    public static void zzc(int i, List<Integer> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Integer> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zze(i, list, z);
        }
    }

    public static void zzf(int i, List<Float> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzf(i, list, z);
        }
    }

    public static void zza(int i, List<?> list, zzanf zzanf, zzalv<?> zzalv) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zza(i, list, (zzalv) zzalv);
        }
    }

    public static void zzg(int i, List<Integer> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzg(i, list, z);
        }
    }

    public static void zzh(int i, List<Long> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzh(i, list, z);
        }
    }

    public static void zzb(int i, List<?> list, zzanf zzanf, zzalv<?> zzalv) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzb(i, list, (zzalv) zzalv);
        }
    }

    public static void zzi(int i, List<Integer> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzi(i, list, z);
        }
    }

    public static void zzj(int i, List<Long> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzj(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzk(i, list, z);
        }
    }

    public static void zzl(int i, List<Long> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzl(i, list, z);
        }
    }

    public static void zzb(int i, List<String> list, zzanf zzanf) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzb(i, list);
        }
    }

    public static void zzm(int i, List<Integer> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzm(i, list, z);
        }
    }

    public static void zzn(int i, List<Long> list, zzanf zzanf, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzanf.zzn(i, list, z);
        }
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
