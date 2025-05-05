package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzpa {
    public static final /* synthetic */ int zza = 0;
    private static final zzpn zzb = new zzpp();

    static {
        int i = zzou.zza;
    }

    public static void zzA(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzA(i, list, z);
        }
    }

    public static void zzB(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzC(i, list, z);
        }
    }

    public static void zzC(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzE(i, list, z);
        }
    }

    public static void zzD(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzJ(i, list, z);
        }
    }

    public static void zzE(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzL(i, list, z);
        }
    }

    static boolean zzF(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }

    static int zza(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            i = 0;
            while (i2 < size) {
                i += zzlp.zzB((long) zzng.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlp.zzB((long) ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzlp.zzA(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzlp.zzA(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            i = 0;
            while (i2 < size) {
                i += zzlp.zzB((long) zzng.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlp.zzB((long) ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzg(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            i = 0;
            while (i2 < size) {
                i += zzlp.zzB(zznz.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlp.zzB(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, Object obj, zzoy zzoy) {
        int i2 = i << 3;
        if (!(obj instanceof zznv)) {
            return zzlp.zzA(i2) + zzlp.zzy((zzok) obj, zzoy);
        }
        int zzA = zzlp.zzA(i2);
        int zza2 = ((zznv) obj).zza();
        return zzA + zzlp.zzA(zza2) + zza2;
    }

    static int zzi(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            i = 0;
            while (i2 < size) {
                int zze = zzng.zze(i2);
                i += zzlp.zzA((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzlp.zzA((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzj(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            i = 0;
            while (i2 < size) {
                long zze = zznz.zze(i2);
                i += zzlp.zzB((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzlp.zzB((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            i = 0;
            while (i2 < size) {
                i += zzlp.zzA(zzng.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlp.zzA(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzl(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            i = 0;
            while (i2 < size) {
                i += zzlp.zzB(zznz.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzlp.zzB(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzpn zzm() {
        return zzb;
    }

    static Object zzn(Object obj, int i, List list, zznj zznj, Object obj2, zzpn zzpn) {
        if (zznj == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zznj.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj2 = zzo(obj, i, intValue, obj2, zzpn);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zznj.zza(intValue2)) {
                    obj2 = zzo(obj, i, intValue2, obj2, zzpn);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object zzo(Object obj, int i, int i2, Object obj2, zzpn zzpn) {
        if (obj2 == null) {
            obj2 = zzpn.zza(obj);
        }
        zzpn.zzh(obj2, i, (long) i2);
        return obj2;
    }

    static void zzp(zzmr zzmr, Object obj, Object obj2) {
        zzmv zzmv = ((zznc) obj2).zzb;
        if (!zzmv.zza.isEmpty()) {
            ((zznc) obj).zzi().zzh(zzmv);
        }
    }

    static void zzq(zzpn zzpn, Object obj, Object obj2) {
        zznf zznf = (zznf) obj;
        zzpo zzpo = zznf.zzc;
        zzpo zzpo2 = ((zznf) obj2).zzc;
        zzpo zzpo3 = zzpo;
        zzpo zzpo4 = zzpo2;
        if (!zzpo.zzc().equals(zzpo2)) {
            if (zzpo.zzc().equals(zzpo)) {
                zzpo = zzpo.zze(zzpo, zzpo2);
            } else {
                zzpo.zzd(zzpo2);
            }
        }
        zzpo zzpo5 = zzpo;
        zznf.zzc = zzpo;
    }

    public static void zzr(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzc(i, list, z);
        }
    }

    public static void zzs(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzg(i, list, z);
        }
    }

    public static void zzt(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzj(i, list, z);
        }
    }

    public static void zzu(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzl(i, list, z);
        }
    }

    public static void zzv(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzn(i, list, z);
        }
    }

    public static void zzw(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzp(i, list, z);
        }
    }

    public static void zzx(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzs(i, list, z);
        }
    }

    public static void zzy(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzu(i, list, z);
        }
    }

    public static void zzz(int i, List list, zzqa zzqa, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzqa.zzy(i, list, z);
        }
    }
}
