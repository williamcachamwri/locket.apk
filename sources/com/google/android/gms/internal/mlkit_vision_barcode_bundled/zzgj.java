package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzgj {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzgy zzc;
    private static final zzgy zzd = new zzha();

    static {
        Class<?> cls;
        Class<?> cls2;
        zzgy zzgy = null;
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
                zzgy = (zzgy) cls2.getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzgy;
    }

    static Object zzA(Object obj, int i, int i2, Object obj2, zzgy zzgy) {
        if (obj2 == null) {
            obj2 = zzgy.zzc(obj);
        }
        zzgy.zzf(obj2, i, (long) i2);
        return obj2;
    }

    static void zzB(zzdp zzdp, Object obj, Object obj2) {
        zzdt zzb2 = zzdp.zzb(obj2);
        if (!zzb2.zza.isEmpty()) {
            zzdp.zzc(obj).zzh(zzb2);
        }
    }

    static void zzC(zzgy zzgy, Object obj, Object obj2) {
        zzgy.zzh(obj, zzgy.zze(zzgy.zzd(obj), zzgy.zzd(obj2)));
    }

    public static void zzD(Class cls) {
        Class cls2;
        if (!zzed.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzE(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzc(i, list, z);
        }
    }

    public static void zzF(int i, List list, zzhq zzhq) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zze(i, list);
        }
    }

    public static void zzG(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzg(i, list, z);
        }
    }

    public static void zzH(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzj(i, list, z);
        }
    }

    public static void zzI(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzl(i, list, z);
        }
    }

    public static void zzJ(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzn(i, list, z);
        }
    }

    public static void zzK(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzp(i, list, z);
        }
    }

    public static void zzL(int i, List list, zzhq zzhq, zzgh zzgh) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzdk) zzhq).zzq(i, list.get(i2), zzgh);
            }
        }
    }

    public static void zzM(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzs(i, list, z);
        }
    }

    public static void zzN(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzu(i, list, z);
        }
    }

    public static void zzO(int i, List list, zzhq zzhq, zzgh zzgh) throws IOException {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((zzdk) zzhq).zzv(i, list.get(i2), zzgh);
            }
        }
    }

    public static void zzP(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzy(i, list, z);
        }
    }

    public static void zzQ(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzA(i, list, z);
        }
    }

    public static void zzR(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzC(i, list, z);
        }
    }

    public static void zzS(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzE(i, list, z);
        }
    }

    public static void zzT(int i, List list, zzhq zzhq) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzH(i, list);
        }
    }

    public static void zzU(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzJ(i, list, z);
        }
    }

    public static void zzV(int i, List list, zzhq zzhq, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            zzhq.zzL(i, list, z);
        }
    }

    static boolean zzW(Object obj, Object obj2) {
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
        return size * (zzdj.zzy(i << 3) + 1);
    }

    static int zzb(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = size * zzdj.zzy(i << 3);
        for (int i2 = 0; i2 < list.size(); i2++) {
            int zzd2 = ((zzdb) list.get(i2)).zzd();
            zzy += zzdj.zzy(zzd2) + zzd2;
        }
        return zzy;
    }

    static int zzc(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzdj.zzy(i << 3));
    }

    static int zzd(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzee) {
            zzee zzee = (zzee) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzu(zzee.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdj.zzu(((Integer) list.get(i2)).intValue());
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
        return size * (zzdj.zzy(i << 3) + 4);
    }

    static int zzf(List list) {
        return list.size() * 4;
    }

    static int zzg(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzdj.zzy(i << 3) + 8);
    }

    static int zzh(List list) {
        return list.size() * 8;
    }

    static int zzi(int i, List list, zzgh zzgh) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzdj.zzt(i, (zzfo) list.get(i3), zzgh);
        }
        return i2;
    }

    static int zzj(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzk(list) + (size * zzdj.zzy(i << 3));
    }

    static int zzk(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzee) {
            zzee zzee = (zzee) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzu(zzee.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdj.zzu(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    static int zzl(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzm(list) + (list.size() * zzdj.zzy(i << 3));
    }

    static int zzm(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfd) {
            zzfd zzfd = (zzfd) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzz(zzfd.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdj.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    static int zzn(int i, Object obj, zzgh zzgh) {
        if (obj instanceof zzeu) {
            int i2 = zzdj.zzb;
            int zza2 = ((zzeu) obj).zza();
            return zzdj.zzy(i << 3) + zzdj.zzy(zza2) + zza2;
        }
        return zzdj.zzy(i << 3) + zzdj.zzw((zzfo) obj, zzgh);
    }

    static int zzo(int i, List list, zzgh zzgh) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzy = zzdj.zzy(i << 3) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzeu) {
                int zza2 = ((zzeu) obj).zza();
                zzy += zzdj.zzy(zza2) + zza2;
            } else {
                zzy += zzdj.zzw((zzfo) obj, zzgh);
            }
        }
        return zzy;
    }

    static int zzp(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzq(list) + (size * zzdj.zzy(i << 3));
    }

    static int zzq(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzee) {
            zzee zzee = (zzee) list;
            i = 0;
            while (i2 < size) {
                int zze = zzee.zze(i2);
                i += zzdj.zzy((zze >> 31) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                int intValue = ((Integer) list.get(i2)).intValue();
                i3 = i + zzdj.zzy((intValue >> 31) ^ (intValue + intValue));
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
        return zzs(list) + (size * zzdj.zzy(i << 3));
    }

    static int zzs(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfd) {
            zzfd zzfd = (zzfd) list;
            i = 0;
            while (i2 < size) {
                long zze = zzfd.zze(i2);
                i += zzdj.zzz((zze >> 63) ^ (zze + zze));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                long longValue = ((Long) list.get(i2)).longValue();
                i3 = i + zzdj.zzz((longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
        }
        return i;
    }

    static int zzt(int i, List list) {
        int zzx;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        boolean z = list instanceof zzew;
        int zzy = zzdj.zzy(i << 3) * size;
        if (z) {
            zzew zzew = (zzew) list;
            while (i2 < size) {
                Object zzf = zzew.zzf(i2);
                if (zzf instanceof zzdb) {
                    int zzd2 = ((zzdb) zzf).zzd();
                    zzy += zzdj.zzy(zzd2) + zzd2;
                } else {
                    zzy += zzdj.zzx((String) zzf);
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzdb) {
                    int zzd3 = ((zzdb) obj).zzd();
                    zzx = zzy + zzdj.zzy(zzd3) + zzd3;
                } else {
                    zzx = zzy + zzdj.zzx((String) obj);
                }
                i2++;
            }
        }
        return zzy;
    }

    static int zzu(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzdj.zzy(i << 3));
    }

    static int zzv(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzee) {
            zzee zzee = (zzee) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzy(zzee.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdj.zzy(((Integer) list.get(i2)).intValue());
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
        return zzx(list) + (size * zzdj.zzy(i << 3));
    }

    static int zzx(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfd) {
            zzfd zzfd = (zzfd) list;
            i = 0;
            while (i2 < size) {
                i += zzdj.zzz(zzfd.zze(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzdj.zzz(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zzgy zzy() {
        return zzc;
    }

    public static zzgy zzz() {
        return zzd;
    }
}
