package com.google.android.gms.internal.atv_ads_framework;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzfa {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzfp zzc;
    private static final zzfp zzd = new zzfr();

    static {
        Class<?> cls;
        Class<?> cls2;
        zzfp zzfp = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzfp = (zzfp) cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzfp;
    }

    static void zzA(zzfp zzfp, Object obj, Object obj2) {
        zzfp.zzf(obj, zzfp.zzd(zzfp.zzc(obj), zzfp.zzc(obj2)));
    }

    public static void zzB(Class cls) {
        Class cls2;
        if (!zzdh.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzC(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzc(i, list, z);
        }
    }

    public static void zzD(int i, List list, zzgg zzgg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zze(i, list);
        }
    }

    public static void zzE(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzg(i, list, z);
        }
    }

    public static void zzF(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzi(i, list, z);
        }
    }

    public static void zzG(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzk(i, list, z);
        }
    }

    public static void zzH(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzm(i, list, z);
        }
    }

    public static void zzI(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzo(i, list, z);
        }
    }

    public static void zzJ(int i, List list, zzgg zzgg, zzey zzey) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzcw) zzgg).zzp(i, list.get(i2), zzey);
            }
        }
    }

    public static void zzK(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzr(i, list, z);
        }
    }

    public static void zzL(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzt(i, list, z);
        }
    }

    public static void zzM(int i, List list, zzgg zzgg, zzey zzey) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzcw) zzgg).zzu(i, list.get(i2), zzey);
            }
        }
    }

    public static void zzN(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzw(i, list, z);
        }
    }

    public static void zzO(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzy(i, list, z);
        }
    }

    public static void zzP(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzA(i, list, z);
        }
    }

    public static void zzQ(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzC(i, list, z);
        }
    }

    public static void zzR(int i, List list, zzgg zzgg) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzE(i, list);
        }
    }

    public static void zzS(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzG(i, list, z);
        }
    }

    public static void zzT(int i, List list, zzgg zzgg, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzgg.zzI(i, list, z);
        }
    }

    static boolean zzU(Object obj, Object obj2) {
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
        return size * (zzcv.zzx(i << 3) + 1);
    }

    static int zzb(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzx = size * zzcv.zzx(i << 3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            int zzd2 = ((zzcn) list.get(i2)).zzd();
            zzx += zzcv.zzx(zzd2) + zzd2;
        }
        return zzx;
    }

    static int zzc(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzcv.zzx(i << 3));
    }

    static int zzd(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdi) {
            zzdi zzdi = (zzdi) list;
            i = 0;
            while (i2 < size) {
                i += zzcv.zzu(zzdi.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzcv.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zze(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzcv.zzx(i << 3) + 4);
    }

    static int zzf(List list) {
        return list.size() * 4;
    }

    static int zzg(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzcv.zzx(i << 3) + 8);
    }

    static int zzh(List list) {
        return list.size() * 8;
    }

    static int zzi(int i, List list, zzey zzey) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzcv.zzt(i, (zzeo) list.get(i3), zzey);
        }
        return i2;
    }

    static int zzj(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzcv.zzx(i << 3));
    }

    static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdi) {
            zzdi zzdi = (zzdi) list;
            i = 0;
            while (i2 < size) {
                i += zzcv.zzu(zzdi.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzcv.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzl(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzm(list) + (list.size() * zzcv.zzx(i << 3));
    }

    static int zzm(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzed = (zzed) list;
            i = 0;
            while (i2 < size) {
                i += zzcv.zzy(zzed.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzcv.zzy(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzn(int i, Object obj, zzey zzey) {
        if (obj instanceof zzdu) {
            int i2 = zzcv.zzb;
            int zza2 = ((zzdu) obj).zza();
            return zzcv.zzx(i << 3) + zzcv.zzx(zza2) + zza2;
        }
        return zzcv.zzx(i << 3) + zzcv.zzv((zzeo) obj, zzey);
    }

    static int zzo(int i, List list, zzey zzey) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzx = zzcv.zzx(i << 3) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzdu) {
                int zza2 = ((zzdu) obj).zza();
                zzx += zzcv.zzx(zza2) + zza2;
            } else {
                zzx += zzcv.zzv((zzeo) obj, zzey);
            }
        }
        return zzx;
    }

    static int zzp(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzq(list) + (size * zzcv.zzx(i << 3));
    }

    static int zzq(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdi) {
            zzdi zzdi = (zzdi) list;
            i = 0;
            while (i2 < size) {
                int zzd2 = zzdi.zzd(i2);
                i += zzcv.zzx((zzd2 >> 31) ^ (zzd2 + zzd2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzcv.zzx((intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
        }
        return i;
    }

    static int zzr(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzcv.zzx(i << 3));
    }

    static int zzs(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzed = (zzed) list;
            i = 0;
            while (i2 < size) {
                long zzd2 = zzed.zzd(i2);
                i += zzcv.zzy((zzd2 >> 63) ^ (zzd2 + zzd2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzcv.zzy((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List list) {
        int zzw;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        boolean z = list instanceof zzdw;
        int zzx = zzcv.zzx(i << 3) * size;
        if (z) {
            zzdw zzdw = (zzdw) list;
            while (i2 < size) {
                Object zze = zzdw.zze(i2);
                if (zze instanceof zzcn) {
                    int zzd2 = ((zzcn) zze).zzd();
                    zzx += zzcv.zzx(zzd2) + zzd2;
                } else {
                    zzx += zzcv.zzw((String) zze);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzcn) {
                    int zzd3 = ((zzcn) obj).zzd();
                    zzw = zzx + zzcv.zzx(zzd3) + zzd3;
                } else {
                    zzw = zzx + zzcv.zzw((String) obj);
                }
                i2++;
            }
        }
        return zzx;
    }

    static int zzu(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzcv.zzx(i << 3));
    }

    static int zzv(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdi) {
            zzdi zzdi = (zzdi) list;
            i = 0;
            while (i2 < size) {
                i += zzcv.zzx(zzdi.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzcv.zzx(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzw(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzx(list) + (size * zzcv.zzx(i << 3));
    }

    static int zzx(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzed) {
            zzed zzed = (zzed) list;
            i = 0;
            while (i2 < size) {
                i += zzcv.zzy(zzed.zzd(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzcv.zzy(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzfp zzy() {
        return zzc;
    }

    public static zzfp zzz() {
        return zzd;
    }
}
