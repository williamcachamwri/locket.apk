package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzajj implements zzanf {
    private final zzajg zza;

    public static zzajj zza(zzajg zzajg) {
        if (zzajg.zze != null) {
            return zzajg.zze;
        }
        return new zzajj(zzajg);
    }

    public final int zza() {
        return 1;
    }

    private zzajj(zzajg zzajg) {
        zzajg zzajg2 = (zzajg) zzakb.zza(zzajg, "output");
        this.zza = zzajg2;
        zzajg2.zze = this;
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zza.zzb(i, z);
    }

    public final void zza(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzain) {
            zzain zzain = (zzain) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzain.size(); i4++) {
                    i3 += zzajg.zza(zzain.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzain.size()) {
                    this.zza.zzb(zzain.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzain.size()) {
                this.zza.zzb(i, zzain.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zza(list.get(i6).booleanValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).booleanValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).booleanValue());
                i2++;
            }
        }
    }

    public final void zza(int i, zzaip zzaip) throws IOException {
        this.zza.zzc(i, zzaip);
    }

    public final void zza(int i, List<zzaip> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zzc(i, list.get(i2));
        }
    }

    public final void zza(int i, double d) throws IOException {
        this.zza.zzb(i, d);
    }

    public final void zzb(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzaji) {
            zzaji zzaji = (zzaji) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzaji.size(); i4++) {
                    i3 += zzajg.zza(zzaji.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzaji.size()) {
                    this.zza.zzb(zzaji.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzaji.size()) {
                this.zza.zzb(i, zzaji.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zza(list.get(i6).doubleValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).doubleValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).doubleValue());
                i2++;
            }
        }
    }

    @Deprecated
    public final void zza(int i) throws IOException {
        this.zza.zzk(i, 4);
    }

    public final void zza(int i, int i2) throws IOException {
        this.zza.zzi(i, i2);
    }

    public final void zzc(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzajz.size(); i4++) {
                    i3 += zzajg.zzc(zzajz.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzajz.size()) {
                    this.zza.zzl(zzajz.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajz.size()) {
                this.zza.zzi(i, zzajz.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzc(list.get(i6).intValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzl(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzi(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzb(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    public final void zzd(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzajz.size(); i4++) {
                    i3 += zzajg.zzd(zzajz.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzajz.size()) {
                    this.zza.zzk(zzajz.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajz.size()) {
                this.zza.zzh(i, zzajz.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzd(list.get(i6).intValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zza(int i, long j) throws IOException {
        this.zza.zzf(i, j);
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzakr.size(); i4++) {
                    i3 += zzajg.zzc(zzakr.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzakr.size()) {
                    this.zza.zzh(zzakr.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakr.size()) {
                this.zza.zzf(i, zzakr.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzc(list.get(i6).longValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final void zza(int i, float f) throws IOException {
        this.zza.zzb(i, f);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajx) {
            zzajx zzajx = (zzajx) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzajx.size(); i4++) {
                    i3 += zzajg.zza(zzajx.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzajx.size()) {
                    this.zza.zzb(zzajx.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajx.size()) {
                this.zza.zzb(i, zzajx.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zza(list.get(i6).floatValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).floatValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).floatValue());
                i2++;
            }
        }
    }

    public final void zza(int i, Object obj, zzalv zzalv) throws IOException {
        zzajg zzajg = this.zza;
        zzajg.zzk(i, 3);
        zzalv.zza((zzalc) obj, (zzanf) zzajg.zze);
        zzajg.zzk(i, 4);
    }

    public final void zza(int i, List<?> list, zzalv zzalv) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzalv);
        }
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zza.zzi(i, i2);
    }

    public final void zzg(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzajz.size(); i4++) {
                    i3 += zzajg.zze(zzajz.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzajz.size()) {
                    this.zza.zzl(zzajz.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajz.size()) {
                this.zza.zzi(i, zzajz.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zze(list.get(i6).intValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzl(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzi(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzb(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzh(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzakr.size(); i4++) {
                    i3 += zzajg.zzd(zzakr.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzakr.size()) {
                    this.zza.zzj(zzakr.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakr.size()) {
                this.zza.zzh(i, zzakr.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzd(list.get(i6).longValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzj(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final <K, V> void zza(int i, zzakx<K, V> zzakx, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zza.zzk(i, 2);
            this.zza.zzn(zzaku.zza(zzakx, next.getKey(), next.getValue()));
            zzaku.zza(this.zza, zzakx, next.getKey(), next.getValue());
        }
    }

    public final void zzb(int i, Object obj, zzalv zzalv) throws IOException {
        this.zza.zzc(i, (zzalc) obj, zzalv);
    }

    public final void zzb(int i, List<?> list, zzalv zzalv) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzalv);
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzaip) {
            this.zza.zzd(i, (zzaip) obj);
        } else {
            this.zza.zzb(i, (zzalc) obj);
        }
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zza.zzh(i, i2);
    }

    public final void zzi(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzajz.size(); i4++) {
                    i3 += zzajg.zzg(zzajz.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzajz.size()) {
                    this.zza.zzk(zzajz.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajz.size()) {
                this.zza.zzh(i, zzajz.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzg(list.get(i6).intValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzc(int i, long j) throws IOException {
        this.zza.zzf(i, j);
    }

    public final void zzj(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzakr.size(); i4++) {
                    i3 += zzajg.zze(zzakr.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzakr.size()) {
                    this.zza.zzh(zzakr.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakr.size()) {
                this.zza.zzf(i, zzakr.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zze(list.get(i6).longValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzf(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final void zze(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzajz.size(); i4++) {
                    i3 += zzajg.zzh(zzajz.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzajz.size()) {
                    this.zza.zzm(zzajz.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajz.size()) {
                this.zza.zzj(i, zzajz.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzh(list.get(i6).intValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzm(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzj(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzd(int i, long j) throws IOException {
        this.zza.zzg(i, j);
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzakr.size(); i4++) {
                    i3 += zzajg.zzf(zzakr.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzakr.size()) {
                    this.zza.zzi(zzakr.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakr.size()) {
                this.zza.zzg(i, zzakr.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzf(list.get(i6).longValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzi(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzg(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    @Deprecated
    public final void zzb(int i) throws IOException {
        this.zza.zzk(i, 3);
    }

    public final void zza(int i, String str) throws IOException {
        this.zza.zzb(i, str);
    }

    public final void zzb(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzakn) {
            zzakn zzakn = (zzakn) list;
            while (i2 < list.size()) {
                Object zza2 = zzakn.zza(i2);
                if (zza2 instanceof String) {
                    this.zza.zzb(i, (String) zza2);
                } else {
                    this.zza.zzc(i, (zzaip) zza2);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zzb(i, list.get(i2));
            i2++;
        }
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zza.zzl(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzajz) {
            zzajz zzajz = (zzajz) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzajz.size(); i4++) {
                    i3 += zzajg.zzj(zzajz.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzajz.size()) {
                    this.zza.zzn(zzajz.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzajz.size()) {
                this.zza.zzl(i, zzajz.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzj(list.get(i6).intValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzn(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzl(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zze(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzakr) {
            zzakr zzakr = (zzakr) list;
            if (z) {
                this.zza.zzk(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzakr.size(); i4++) {
                    i3 += zzajg.zzg(zzakr.zzb(i4));
                }
                this.zza.zzn(i3);
                while (i2 < zzakr.size()) {
                    this.zza.zzj(zzakr.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzakr.size()) {
                this.zza.zzh(i, zzakr.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzk(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzajg.zzg(list.get(i6).longValue());
            }
            this.zza.zzn(i5);
            while (i2 < list.size()) {
                this.zza.zzj(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).longValue());
                i2++;
            }
        }
    }
}
