package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzadg implements zzagu {
    private final zzadf zza;

    private zzadg(zzadf zzadf) {
        byte[] bArr = zzaee.zzb;
        zzadf zzadf2 = zzadf;
        this.zza = zzadf;
        zzadf.zza = this;
    }

    public static zzadg zza(zzadf zzadf) {
        zzadg zzadg = zzadf.zza;
        return zzadg != null ? zzadg : new zzadg(zzadf);
    }

    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzs(i, (i2 >> 31) ^ (i2 + i2));
    }

    public final void zzD(int i, long j) throws IOException {
        this.zza.zzu(i, (j >> 63) ^ (j + j));
    }

    @Deprecated
    public final void zzF(int i) throws IOException {
        this.zza.zzr(i, 3);
    }

    public final void zzG(int i, String str) throws IOException {
        this.zza.zzp(i, str);
    }

    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzs(i, i2);
    }

    public final void zzK(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    public final void zzd(int i, zzacw zzacw) throws IOException {
        this.zza.zze(i, zzacw);
    }

    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzacw) list.get(i2));
        }
    }

    public final void zzf(int i, double d) throws IOException {
        this.zza.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Deprecated
    public final void zzh(int i) throws IOException {
        this.zza.zzr(i, 4);
    }

    public final void zzi(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    public final void zzk(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    public final void zzm(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzo(int i, float f) throws IOException {
        this.zza.zzf(i, Float.floatToRawIntBits(f));
    }

    public final void zzq(int i, Object obj, zzaft zzaft) throws IOException {
        zzadf zzadf = this.zza;
        zzadf.zzr(i, 3);
        zzaft.zzj((zzafb) obj, zzadf.zza);
        zzadf.zzr(i, 4);
    }

    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    public final void zzt(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    public final void zzv(int i, Object obj, zzaft zzaft) throws IOException {
        this.zza.zzm(i, (zzafb) obj, zzaft);
    }

    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzacw) {
            this.zza.zzo(i, (zzacw) obj);
        } else {
            this.zza.zzn(i, (zzafb) obj);
        }
    }

    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    public final void zzz(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (list instanceof zzaen) {
            zzaen zzaen = (zzaen) list;
            while (i2 < list.size()) {
                Object zzb = zzaen.zzb();
                if (zzb instanceof String) {
                    this.zza.zzp(i, (String) zzb);
                } else {
                    this.zza.zze(i, (zzacw) zzb);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzp(i, (String) list.get(i2));
            i2++;
        }
    }

    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadz.size(); i4++) {
                    i3 += zzadf.zzz(zzadz.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzadz.size()) {
                    this.zza.zzt(zzadz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzadz.size()) {
                this.zza.zzs(i, zzadz.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzadf.zzz(((Integer) list.get(i6)).intValue());
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzt(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzs(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzaeq.size(); i4++) {
                    i3 += zzadf.zzA(zzaeq.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzaeq.size()) {
                    this.zza.zzv(zzaeq.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzaeq.size()) {
                this.zza.zzu(i, zzaeq.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzadf.zzA(((Long) list.get(i6)).longValue());
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadz.size(); i4++) {
                    zzadz.zze(i4);
                    i3 += 4;
                }
                this.zza.zzt(i3);
                while (i2 < zzadz.size()) {
                    this.zza.zzg(zzadz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzadz.size()) {
                this.zza.zzf(i, zzadz.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzn(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzaeq.size(); i4++) {
                    zzaeq.zze(i4);
                    i3 += 8;
                }
                this.zza.zzt(i3);
                while (i2 < zzaeq.size()) {
                    this.zza.zzi(zzaeq.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzaeq.size()) {
                this.zza.zzh(i, zzaeq.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzacn) {
            zzacn zzacn = (zzacn) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzacn.size(); i4++) {
                    zzacn.zzf(i4);
                    i3++;
                }
                this.zza.zzt(i3);
                while (i2 < zzacn.size()) {
                    this.zza.zzb(zzacn.zzf(i2) ? (byte) 1 : 0);
                    i2++;
                }
                return;
            }
            while (i2 < zzacn.size()) {
                this.zza.zzd(i, zzacn.zzf(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Boolean) list.get(i6)).booleanValue();
                i5++;
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : 0);
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzd(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
        }
    }

    public final void zzs(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadz.size(); i4++) {
                    i3 += zzadf.zzA((long) zzadz.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzadz.size()) {
                    this.zza.zzk(zzadz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzadz.size()) {
                this.zza.zzj(i, zzadz.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzadf.zzA((long) ((Integer) list.get(i6)).intValue());
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzaeq.size(); i4++) {
                    zzaeq.zze(i4);
                    i3 += 8;
                }
                this.zza.zzt(i3);
                while (i2 < zzaeq.size()) {
                    this.zza.zzi(zzaeq.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzaeq.size()) {
                this.zza.zzh(i, zzaeq.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Long) list.get(i6)).longValue();
                i5 += 8;
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadh) {
            zzadh zzadh = (zzadh) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadh.size(); i4++) {
                    zzadh.zze(i4);
                    i3 += 8;
                }
                this.zza.zzt(i3);
                while (i2 < zzadh.size()) {
                    this.zza.zzi(Double.doubleToRawLongBits(zzadh.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzadh.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(zzadh.zze(i2)));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Double) list.get(i6)).doubleValue();
                i5 += 8;
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
        }
    }

    public final void zzp(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadr) {
            zzadr zzadr = (zzadr) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadr.size(); i4++) {
                    zzadr.zze(i4);
                    i3 += 4;
                }
                this.zza.zzt(i3);
                while (i2 < zzadr.size()) {
                    this.zza.zzg(Float.floatToRawIntBits(zzadr.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzadr.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(zzadr.zze(i2)));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Float) list.get(i6)).floatValue();
                i5 += 4;
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
        }
    }

    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadz.size(); i4++) {
                    zzadz.zze(i4);
                    i3 += 4;
                }
                this.zza.zzt(i3);
                while (i2 < zzadz.size()) {
                    this.zza.zzg(zzadz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzadz.size()) {
                this.zza.zzf(i, zzadz.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                ((Integer) list.get(i6)).intValue();
                i5 += 4;
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadz.size(); i4++) {
                    int zze = zzadz.zze(i4);
                    i3 += zzadf.zzz((zze >> 31) ^ (zze + zze));
                }
                this.zza.zzt(i3);
                while (i2 < zzadz.size()) {
                    zzadf zzadf = this.zza;
                    int zze2 = zzadz.zze(i2);
                    zzadf.zzt((zze2 >> 31) ^ (zze2 + zze2));
                    i2++;
                }
                return;
            }
            while (i2 < zzadz.size()) {
                zzadf zzadf2 = this.zza;
                int zze3 = zzadz.zze(i2);
                zzadf2.zzs(i, (zze3 >> 31) ^ (zze3 + zze3));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                int intValue = ((Integer) list.get(i6)).intValue();
                i5 += zzadf.zzz((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                zzadf zzadf3 = this.zza;
                int intValue2 = ((Integer) list.get(i2)).intValue();
                zzadf3.zzt((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzadf zzadf4 = this.zza;
                int intValue3 = ((Integer) list.get(i2)).intValue();
                zzadf4.zzs(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
                i2++;
            }
        }
    }

    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzaeq.size(); i4++) {
                    long zze = zzaeq.zze(i4);
                    i3 += zzadf.zzA((zze >> 63) ^ (zze + zze));
                }
                this.zza.zzt(i3);
                while (i2 < zzaeq.size()) {
                    zzadf zzadf = this.zza;
                    long zze2 = zzaeq.zze(i2);
                    zzadf.zzv((zze2 >> 63) ^ (zze2 + zze2));
                    i2++;
                }
                return;
            }
            while (i2 < zzaeq.size()) {
                zzadf zzadf2 = this.zza;
                long zze3 = zzaeq.zze(i2);
                zzadf2.zzu(i, (zze3 >> 63) ^ (zze3 + zze3));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                long longValue = ((Long) list.get(i6)).longValue();
                i5 += zzadf.zzA((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                zzadf zzadf3 = this.zza;
                long longValue2 = ((Long) list.get(i2)).longValue();
                zzadf3.zzv((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzadf zzadf4 = this.zza;
                long longValue3 = ((Long) list.get(i2)).longValue();
                zzadf4.zzu(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
                i2++;
            }
        }
    }

    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzadz.size(); i4++) {
                    i3 += zzadf.zzA((long) zzadz.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzadz.size()) {
                    this.zza.zzk(zzadz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzadz.size()) {
                this.zza.zzj(i, zzadz.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzadf.zzA((long) ((Integer) list.get(i6)).intValue());
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public final void zzu(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzaeq.size(); i4++) {
                    i3 += zzadf.zzA(zzaeq.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzaeq.size()) {
                    this.zza.zzv(zzaeq.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzaeq.size()) {
                this.zza.zzu(i, zzaeq.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzadf.zzA(((Long) list.get(i6)).longValue());
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                this.zza.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }
}
