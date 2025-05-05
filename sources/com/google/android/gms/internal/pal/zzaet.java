package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaet {
    private static final Class zza;
    private static final zzafi zzb = zzab(false);
    private static final zzafi zzc = zzab(true);
    private static final zzafi zzd = new zzafk();

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
    }

    public static zzafi zzA() {
        return zzc;
    }

    public static zzafi zzB() {
        return zzd;
    }

    static Object zzC(int i, List list, zzadd zzadd, Object obj, zzafi zzafi) {
        if (zzadd == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzadd.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj = zzD(i, intValue, obj, zzafi);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzadd.zza(intValue2)) {
                    obj = zzD(i, intValue2, obj, zzafi);
                    it.remove();
                }
            }
        }
        return obj;
    }

    static Object zzD(int i, int i2, Object obj, zzafi zzafi) {
        if (obj == null) {
            obj = zzafi.zzf();
        }
        zzafi.zzl(obj, i, (long) i2);
        return obj;
    }

    static void zzE(zzacn zzacn, Object obj, Object obj2) {
        zzacn.zza(obj2);
        throw null;
    }

    static void zzF(zzafi zzafi, Object obj, Object obj2) {
        zzafi.zzo(obj, zzafi.zze(zzafi.zzd(obj), zzafi.zzd(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzacz.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzH(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzc(i, list, z);
        }
    }

    public static void zzI(int i, List list, zzaga zzaga) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zze(i, list);
        }
    }

    public static void zzJ(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzg(i, list, z);
        }
    }

    public static void zzK(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzj(i, list, z);
        }
    }

    public static void zzL(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzl(i, list, z);
        }
    }

    public static void zzM(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzn(i, list, z);
        }
    }

    public static void zzN(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzp(i, list, z);
        }
    }

    public static void zzO(int i, List list, zzaga zzaga, zzaer zzaer) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzaci) zzaga).zzq(i, list.get(i2), zzaer);
            }
        }
    }

    public static void zzP(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzs(i, list, z);
        }
    }

    public static void zzQ(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzu(i, list, z);
        }
    }

    public static void zzR(int i, List list, zzaga zzaga, zzaer zzaer) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzaci) zzaga).zzv(i, list.get(i2), zzaer);
            }
        }
    }

    public static void zzS(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzx(i, list, z);
        }
    }

    public static void zzT(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzz(i, list, z);
        }
    }

    public static void zzU(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzB(i, list, z);
        }
    }

    public static void zzV(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzD(i, list, z);
        }
    }

    public static void zzW(int i, List list, zzaga zzaga) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzG(i, list);
        }
    }

    public static void zzX(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzI(i, list, z);
        }
    }

    public static void zzY(int i, List list, zzaga zzaga, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzaga.zzK(i, list, z);
        }
    }

    static boolean zzZ(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    static int zza(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzach.zzA(i << 3) + 1);
    }

    static void zzaa(zzaea zzaea, Object obj, Object obj2, long j) {
        zzafs.zzs(obj, j, zzaea.zzc(zzafs.zzf(obj, j), zzafs.zzf(obj2, j)));
    }

    private static zzafi zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzafi) cls.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused2) {
            return null;
        }
    }

    static int zzb(List list) {
        return list.size();
    }

    static int zzc(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = size * zzach.zzz(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzz += zzach.zzt((zzaby) list.get(i2));
        }
        return zzz;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzach.zzz(i));
    }

    static int zze(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            i = 0;
            while (i2 < size) {
                i += zzach.zzv(zzada.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzach.zzv(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzf(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzach.zzA(i << 3) + 4);
    }

    static int zzg(List list) {
        return list.size() * 4;
    }

    static int zzh(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzach.zzA(i << 3) + 8);
    }

    static int zzi(List list) {
        return list.size() * 8;
    }

    static int zzj(int i, List list, zzaer zzaer) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzach.zzu(i, (zzaef) list.get(i3), zzaer);
        }
        return i2;
    }

    static int zzk(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzach.zzz(i));
    }

    static int zzl(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            i = 0;
            while (i2 < size) {
                i += zzach.zzv(zzada.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzach.zzv(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzm(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzach.zzz(i));
    }

    static int zzn(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            i = 0;
            while (i2 < size) {
                i += zzach.zzB(zzadu.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzach.zzB(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzo(int i, Object obj, zzaer zzaer) {
        if (!(obj instanceof zzadl)) {
            return zzach.zzA(i << 3) + zzach.zzx((zzaef) obj, zzaer);
        }
        int zzA = zzach.zzA(i << 3);
        int zza2 = ((zzadl) obj).zza();
        return zzA + zzach.zzA(zza2) + zza2;
    }

    static int zzp(int i, List list, zzaer zzaer) {
        int i2;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzach.zzz(i) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            if (obj instanceof zzadl) {
                i2 = zzach.zzw((zzadl) obj);
            } else {
                i2 = zzach.zzx((zzaef) obj, zzaer);
            }
            zzz += i2;
        }
        return zzz;
    }

    static int zzq(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzach.zzz(i));
    }

    static int zzr(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            i = 0;
            while (i2 < size) {
                int zze = zzada.zze(i2);
                i += zzach.zzA((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzach.zzA((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzs(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzach.zzz(i));
    }

    static int zzt(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            i = 0;
            while (i2 < size) {
                long zze = zzadu.zze(i2);
                i += zzach.zzB((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzach.zzB((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzu(int i, List list) {
        int i2;
        int i3;
        int size = list.size();
        int i4 = 0;
        if (size == 0) {
            return 0;
        }
        int zzz = zzach.zzz(i) * size;
        if (list instanceof zzadn) {
            zzadn zzadn = (zzadn) list;
            while (i4 < size) {
                Object zzf = zzadn.zzf(i4);
                if (zzf instanceof zzaby) {
                    i3 = zzach.zzt((zzaby) zzf);
                } else {
                    i3 = zzach.zzy((String) zzf);
                }
                zzz += i3;
                i4++;
            }
        } else {
            while (i4 < size) {
                Object obj = list.get(i4);
                if (obj instanceof zzaby) {
                    i2 = zzach.zzt((zzaby) obj);
                } else {
                    i2 = zzach.zzy((String) obj);
                }
                zzz += i2;
                i4++;
            }
        }
        return zzz;
    }

    static int zzv(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzach.zzz(i));
    }

    static int zzw(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            i = 0;
            while (i2 < size) {
                i += zzach.zzA(zzada.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzach.zzA(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzx(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzach.zzz(i));
    }

    static int zzy(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            i = 0;
            while (i2 < size) {
                i += zzach.zzB(zzadu.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzach.zzB(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzafi zzz() {
        return zzb;
    }
}
