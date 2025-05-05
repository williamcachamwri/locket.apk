package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzlq implements zzqa {
    private final zzlp zza;

    private zzlq(zzlp zzlp) {
        byte[] bArr = zznn.zzb;
        zzlp zzlp2 = zzlp;
        this.zza = zzlp;
        zzlp.zza = this;
    }

    public static zzlq zza(zzlp zzlp) {
        zzlq zzlq = zzlp.zza;
        return zzlq != null ? zzlq : new zzlq(zzlp);
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

    public final void zzd(int i, zzlg zzlg) throws IOException {
        this.zza.zze(i, zzlg);
    }

    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzlg) list.get(i2));
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

    public final void zzq(int i, Object obj, zzoy zzoy) throws IOException {
        zzlp zzlp = this.zza;
        zzlp.zzr(i, 3);
        zzoy.zzj((zzok) obj, zzlp.zza);
        zzlp.zzr(i, 4);
    }

    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    public final void zzt(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    public final void zzv(int i, Object obj, zzoy zzoy) throws IOException {
        this.zza.zzm(i, (zzok) obj, zzoy);
    }

    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zzlg) {
            this.zza.zzo(i, (zzlg) obj);
        } else {
            this.zza.zzn(i, (zzok) obj);
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
        if (list instanceof zznw) {
            zznw zznw = (zznw) list;
            while (i2 < list.size()) {
                Object zzc = zznw.zzc();
                if (zzc instanceof String) {
                    this.zza.zzp(i, (String) zzc);
                } else {
                    this.zza.zze(i, (zzlg) zzc);
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
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzng.size(); i4++) {
                    i3 += zzlp.zzA(zzng.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzng.size()) {
                    this.zza.zzt(zzng.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzng.size()) {
                this.zza.zzs(i, zzng.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlp.zzA(((Integer) list.get(i6)).intValue());
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
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zznz.size(); i4++) {
                    i3 += zzlp.zzB(zznz.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zznz.size()) {
                    this.zza.zzv(zznz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zznz.size()) {
                this.zza.zzu(i, zznz.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlp.zzB(((Long) list.get(i6)).longValue());
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
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzng.size(); i4++) {
                    zzng.zze(i4);
                    i3 += 4;
                }
                this.zza.zzt(i3);
                while (i2 < zzng.size()) {
                    this.zza.zzg(zzng.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzng.size()) {
                this.zza.zzf(i, zzng.zze(i2));
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
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zznz.size(); i4++) {
                    zznz.zze(i4);
                    i3 += 8;
                }
                this.zza.zzt(i3);
                while (i2 < zznz.size()) {
                    this.zza.zzi(zznz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zznz.size()) {
                this.zza.zzh(i, zznz.zze(i2));
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
        if (list instanceof zzkx) {
            zzkx zzkx = (zzkx) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzkx.size(); i4++) {
                    zzkx.zzf(i4);
                    i3++;
                }
                this.zza.zzt(i3);
                while (i2 < zzkx.size()) {
                    this.zza.zzb(zzkx.zzf(i2) ? (byte) 1 : 0);
                    i2++;
                }
                return;
            }
            while (i2 < zzkx.size()) {
                this.zza.zzd(i, zzkx.zzf(i2));
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
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzng.size(); i4++) {
                    i3 += zzlp.zzB((long) zzng.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzng.size()) {
                    this.zza.zzk(zzng.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzng.size()) {
                this.zza.zzj(i, zzng.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlp.zzB((long) ((Integer) list.get(i6)).intValue());
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
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zznz.size(); i4++) {
                    zznz.zze(i4);
                    i3 += 8;
                }
                this.zza.zzt(i3);
                while (i2 < zznz.size()) {
                    this.zza.zzi(zznz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zznz.size()) {
                this.zza.zzh(i, zznz.zze(i2));
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
        if (list instanceof zzmk) {
            zzmk zzmk = (zzmk) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzmk.size(); i4++) {
                    zzmk.zze(i4);
                    i3 += 8;
                }
                this.zza.zzt(i3);
                while (i2 < zzmk.size()) {
                    this.zza.zzi(Double.doubleToRawLongBits(zzmk.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzmk.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(zzmk.zze(i2)));
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
        if (list instanceof zzmx) {
            zzmx zzmx = (zzmx) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzmx.size(); i4++) {
                    zzmx.zze(i4);
                    i3 += 4;
                }
                this.zza.zzt(i3);
                while (i2 < zzmx.size()) {
                    this.zza.zzg(Float.floatToRawIntBits(zzmx.zze(i2)));
                    i2++;
                }
                return;
            }
            while (i2 < zzmx.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(zzmx.zze(i2)));
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
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzng.size(); i4++) {
                    zzng.zze(i4);
                    i3 += 4;
                }
                this.zza.zzt(i3);
                while (i2 < zzng.size()) {
                    this.zza.zzg(zzng.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzng.size()) {
                this.zza.zzf(i, zzng.zze(i2));
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
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzng.size(); i4++) {
                    int zze = zzng.zze(i4);
                    i3 += zzlp.zzA((zze >> 31) ^ (zze + zze));
                }
                this.zza.zzt(i3);
                while (i2 < zzng.size()) {
                    zzlp zzlp = this.zza;
                    int zze2 = zzng.zze(i2);
                    zzlp.zzt((zze2 >> 31) ^ (zze2 + zze2));
                    i2++;
                }
                return;
            }
            while (i2 < zzng.size()) {
                zzlp zzlp2 = this.zza;
                int zze3 = zzng.zze(i2);
                zzlp2.zzs(i, (zze3 >> 31) ^ (zze3 + zze3));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                int intValue = ((Integer) list.get(i6)).intValue();
                i5 += zzlp.zzA((intValue >> 31) ^ (intValue + intValue));
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                zzlp zzlp3 = this.zza;
                int intValue2 = ((Integer) list.get(i2)).intValue();
                zzlp3.zzt((intValue2 >> 31) ^ (intValue2 + intValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzlp zzlp4 = this.zza;
                int intValue3 = ((Integer) list.get(i2)).intValue();
                zzlp4.zzs(i, (intValue3 >> 31) ^ (intValue3 + intValue3));
                i2++;
            }
        }
    }

    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zznz.size(); i4++) {
                    long zze = zznz.zze(i4);
                    i3 += zzlp.zzB((zze >> 63) ^ (zze + zze));
                }
                this.zza.zzt(i3);
                while (i2 < zznz.size()) {
                    zzlp zzlp = this.zza;
                    long zze2 = zznz.zze(i2);
                    zzlp.zzv((zze2 >> 63) ^ (zze2 + zze2));
                    i2++;
                }
                return;
            }
            while (i2 < zznz.size()) {
                zzlp zzlp2 = this.zza;
                long zze3 = zznz.zze(i2);
                zzlp2.zzu(i, (zze3 >> 63) ^ (zze3 + zze3));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                long longValue = ((Long) list.get(i6)).longValue();
                i5 += zzlp.zzB((longValue >> 63) ^ (longValue + longValue));
            }
            this.zza.zzt(i5);
            while (i2 < list.size()) {
                zzlp zzlp3 = this.zza;
                long longValue2 = ((Long) list.get(i2)).longValue();
                zzlp3.zzv((longValue2 >> 63) ^ (longValue2 + longValue2));
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                zzlp zzlp4 = this.zza;
                long longValue3 = ((Long) list.get(i2)).longValue();
                zzlp4.zzu(i, (longValue3 >> 63) ^ (longValue3 + longValue3));
                i2++;
            }
        }
    }

    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzng.size(); i4++) {
                    i3 += zzlp.zzB((long) zzng.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zzng.size()) {
                    this.zza.zzk(zzng.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzng.size()) {
                this.zza.zzj(i, zzng.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlp.zzB((long) ((Integer) list.get(i6)).intValue());
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
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            if (z) {
                this.zza.zzr(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zznz.size(); i4++) {
                    i3 += zzlp.zzB(zznz.zze(i4));
                }
                this.zza.zzt(i3);
                while (i2 < zznz.size()) {
                    this.zza.zzv(zznz.zze(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zznz.size()) {
                this.zza.zzu(i, zznz.zze(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzr(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzlp.zzB(((Long) list.get(i6)).longValue());
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
