package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzafv {
    public static final /* synthetic */ int zza = 0;
    private static final zzagh zzb = new zzagj();

    static {
        int i = zzafi.zza;
    }

    public static void zzA(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzA(i, list, z);
        }
    }

    public static void zzB(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzC(i, list, z);
        }
    }

    public static void zzC(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzE(i, list, z);
        }
    }

    public static void zzD(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzJ(i, list, z);
        }
    }

    public static void zzE(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzL(i, list, z);
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
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            i = 0;
            while (i2 < size) {
                i += zzadf.zzA((long) zzadz.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzadf.zzA((long) ((Integer) list.get(i2)).intValue());
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
        return size * (zzadf.zzz(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzadf.zzz(i << 3) + 8);
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
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            i = 0;
            while (i2 < size) {
                i += zzadf.zzA((long) zzadz.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzadf.zzA((long) ((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            i = 0;
            while (i2 < size) {
                i += zzadf.zzA(zzaeq.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzadf.zzA(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzh(int i, Object obj, zzaft zzaft) {
        int i2 = i << 3;
        if (!(obj instanceof zzaem)) {
            return zzadf.zzz(i2) + zzadf.zzx((zzafb) obj, zzaft);
        }
        int zzz = zzadf.zzz(i2);
        int zza2 = ((zzaem) obj).zza();
        return zzz + zzadf.zzz(zza2) + zza2;
    }

    static int zzi(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            i = 0;
            while (i2 < size) {
                int zze = zzadz.zze(i2);
                i += zzadf.zzz((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzadf.zzz((intValue >> 31) ^ (intValue + intValue));
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
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            i = 0;
            while (i2 < size) {
                long zze = zzaeq.zze(i2);
                i += zzadf.zzA((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzadf.zzA((longValue >> 63) ^ (longValue + longValue));
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
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            i = 0;
            while (i2 < size) {
                i += zzadf.zzz(zzadz.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzadf.zzz(((Integer) list.get(i2)).intValue());
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
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            i = 0;
            while (i2 < size) {
                i += zzadf.zzA(zzaeq.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzadf.zzA(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzagh zzm() {
        return zzb;
    }

    static Object zzn(Object obj, int i, List list, zzaeb zzaeb, Object obj2, zzagh zzagh) {
        if (zzaeb == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue = ((Integer) list.get(i3)).intValue();
                if (zzaeb.zza(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue));
                    }
                    i2++;
                } else {
                    obj2 = zzo(obj, i, intValue, obj2, zzagh);
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
                if (!zzaeb.zza(intValue2)) {
                    obj2 = zzo(obj, i, intValue2, obj2, zzagh);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    static Object zzo(Object obj, int i, int i2, Object obj2, zzagh zzagh) {
        if (obj2 == null) {
            obj2 = zzagh.zza(obj);
        }
        zzagh.zzh(obj2, i, (long) i2);
        return obj2;
    }

    static void zzp(zzadl zzadl, Object obj, Object obj2) {
        if (!((zzadv) obj2).zzb.zza.isEmpty()) {
            zzadv zzadv = (zzadv) obj;
            throw null;
        }
    }

    static void zzq(zzagh zzagh, Object obj, Object obj2) {
        zzady zzady = (zzady) obj;
        zzagi zzagi = zzady.zzc;
        zzagi zzagi2 = ((zzady) obj2).zzc;
        zzagi zzagi3 = zzagi;
        zzagi zzagi4 = zzagi2;
        if (!zzagi.zzc().equals(zzagi2)) {
            if (zzagi.zzc().equals(zzagi)) {
                zzagi = zzagi.zze(zzagi, zzagi2);
            } else {
                zzagi.zzd(zzagi2);
            }
        }
        zzagi zzagi5 = zzagi;
        zzady.zzc = zzagi;
    }

    public static void zzr(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzc(i, list, z);
        }
    }

    public static void zzs(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzg(i, list, z);
        }
    }

    public static void zzt(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzj(i, list, z);
        }
    }

    public static void zzu(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzl(i, list, z);
        }
    }

    public static void zzv(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzn(i, list, z);
        }
    }

    public static void zzw(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzp(i, list, z);
        }
    }

    public static void zzx(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzs(i, list, z);
        }
    }

    public static void zzy(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzu(i, list, z);
        }
    }

    public static void zzz(int i, List list, zzagu zzagu, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzagu.zzy(i, list, z);
        }
    }
}
