package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzjf implements zznb {
    private final zzjc zza;

    public static zzjf zza(zzjc zzjc) {
        if (zzjc.zza != null) {
            return zzjc.zza;
        }
        return new zzjf(zzjc);
    }

    public final int zza() {
        return 1;
    }

    private zzjf(zzjc zzjc) {
        zzjc zzjc2 = (zzjc) zzjv.zza(zzjc, "output");
        this.zza = zzjc2;
        zzjc2.zza = this;
    }

    public final void zza(int i, boolean z) throws IOException {
        this.zza.zza(i, z);
    }

    public final void zza(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzii) {
            zzii zzii = (zzii) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzii.size(); i4++) {
                    i3 += zzjc.zza(zzii.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzii.size()) {
                    this.zza.zzb(zzii.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzii.size()) {
                this.zza.zza(i, zzii.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zza(list.get(i6).booleanValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).booleanValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).booleanValue());
                i2++;
            }
        }
    }

    public final void zza(int i, zzik zzik) throws IOException {
        this.zza.zza(i, zzik);
    }

    public final void zza(int i, List<zzik> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zza(i, list.get(i2));
        }
    }

    public final void zza(int i, double d) throws IOException {
        this.zza.zzb(i, d);
    }

    public final void zzb(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzje) {
            zzje zzje = (zzje) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzje.size(); i4++) {
                    i3 += zzjc.zza(zzje.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzje.size()) {
                    this.zza.zzb(zzje.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzje.size()) {
                this.zza.zzb(i, zzje.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zza(list.get(i6).doubleValue());
            }
            this.zza.zzc(i5);
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
        this.zza.zzc(i, 4);
    }

    public final void zza(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    public final void zzc(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjw.size(); i4++) {
                    i3 += zzjc.zzd(zzjw.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjw.size()) {
                    this.zza.zzb(zzjw.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjw.size()) {
                this.zza.zzb(i, zzjw.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzd(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzb(int i, int i2) throws IOException {
        this.zza.zza(i, i2);
    }

    public final void zzd(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjw.size(); i4++) {
                    i3 += zzjc.zze(zzjw.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjw.size()) {
                    this.zza.zza(zzjw.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjw.size()) {
                this.zza.zza(i, zzjw.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zze(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zza(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzkn.size(); i4++) {
                    i3 += zzjc.zzc(zzkn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzkn.size()) {
                    this.zza.zza(zzkn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzkn.size()) {
                this.zza.zza(i, zzkn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzc(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final void zza(int i, float f) throws IOException {
        this.zza.zzb(i, f);
    }

    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjs) {
            zzjs zzjs = (zzjs) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjs.size(); i4++) {
                    i3 += zzjc.zza(zzjs.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjs.size()) {
                    this.zza.zzb(zzjs.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjs.size()) {
                this.zza.zzb(i, zzjs.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zza(list.get(i6).floatValue());
            }
            this.zza.zzc(i5);
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

    public final void zza(int i, Object obj, zzlu zzlu) throws IOException {
        zzjc zzjc = this.zza;
        zzjc.zzc(i, 3);
        zzlu.zza((zzlc) obj, (zznb) zzjc.zza);
        zzjc.zzc(i, 4);
    }

    public final void zza(int i, List<?> list, zzlu zzlu) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzlu);
        }
    }

    public final void zzc(int i, int i2) throws IOException {
        this.zza.zzb(i, i2);
    }

    public final void zzg(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjw.size(); i4++) {
                    i3 += zzjc.zzf(zzjw.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjw.size()) {
                    this.zza.zzb(zzjw.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjw.size()) {
                this.zza.zzb(i, zzjw.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzf(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzb(int i, long j) throws IOException {
        this.zza.zzb(i, j);
    }

    public final void zzh(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzkn.size(); i4++) {
                    i3 += zzjc.zzd(zzkn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzkn.size()) {
                    this.zza.zzb(zzkn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzkn.size()) {
                this.zza.zzb(i, zzkn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzd(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final <K, V> void zza(int i, zzkt<K, V> zzkt, Map<K, V> map) throws IOException {
        for (Map.Entry next : map.entrySet()) {
            this.zza.zzc(i, 2);
            this.zza.zzc(zzku.zza(zzkt, next.getKey(), next.getValue()));
            zzku.zza(this.zza, zzkt, next.getKey(), next.getValue());
        }
    }

    public final void zzb(int i, Object obj, zzlu zzlu) throws IOException {
        this.zza.zza(i, (zzlc) obj, zzlu);
    }

    public final void zzb(int i, List<?> list, zzlu zzlu) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzlu);
        }
    }

    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzik) {
            this.zza.zzb(i, (zzik) obj);
        } else {
            this.zza.zza(i, (zzlc) obj);
        }
    }

    public final void zzd(int i, int i2) throws IOException {
        this.zza.zza(i, i2);
    }

    public final void zzi(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjw.size(); i4++) {
                    i3 += zzjc.zzg(zzjw.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjw.size()) {
                    this.zza.zza(zzjw.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjw.size()) {
                this.zza.zza(i, zzjw.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzg(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzc(int i, long j) throws IOException {
        this.zza.zza(i, j);
    }

    public final void zzj(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzkn.size(); i4++) {
                    i3 += zzjc.zze(zzkn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzkn.size()) {
                    this.zza.zza(zzkn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzkn.size()) {
                this.zza.zza(i, zzkn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zze(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zza(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zza(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    public final void zze(int i, int i2) throws IOException {
        this.zza.zzk(i, i2);
    }

    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjw.size(); i4++) {
                    i3 += zzjc.zzh(zzjw.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjw.size()) {
                    this.zza.zzk(zzjw.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjw.size()) {
                this.zza.zzk(i, zzjw.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzh(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzk(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzk(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zzd(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzkn.size(); i4++) {
                    i3 += zzjc.zzf(zzkn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzkn.size()) {
                    this.zza.zzh(zzkn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzkn.size()) {
                this.zza.zzh(i, zzkn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzf(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzh(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzh(i, list.get(i2).longValue());
                i2++;
            }
        }
    }

    @Deprecated
    public final void zzb(int i) throws IOException {
        this.zza.zzc(i, 3);
    }

    public final void zza(int i, String str) throws IOException {
        this.zza.zza(i, str);
    }

    public final void zzb(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzkj) {
            zzkj zzkj = (zzkj) list;
            while (i2 < list.size()) {
                Object zza2 = zzkj.zza(i2);
                if (zza2 instanceof String) {
                    this.zza.zza(i, (String) zza2);
                } else {
                    this.zza.zza(i, (zzik) zza2);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zza.zza(i, list.get(i2));
            i2++;
        }
    }

    public final void zzf(int i, int i2) throws IOException {
        this.zza.zzd(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzjw) {
            zzjw zzjw = (zzjw) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzjw.size(); i4++) {
                    i3 += zzjc.zzj(zzjw.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzjw.size()) {
                    this.zza.zzc(zzjw.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzjw.size()) {
                this.zza.zzd(i, zzjw.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzj(list.get(i6).intValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzc(list.get(i2).intValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzd(i, list.get(i2).intValue());
                i2++;
            }
        }
    }

    public final void zze(int i, long j) throws IOException {
        this.zza.zzb(i, j);
    }

    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            if (z) {
                this.zza.zzc(i, 2);
                int i3 = 0;
                for (int i4 = 0; i4 < zzkn.size(); i4++) {
                    i3 += zzjc.zzg(zzkn.zzb(i4));
                }
                this.zza.zzc(i3);
                while (i2 < zzkn.size()) {
                    this.zza.zzb(zzkn.zzb(i2));
                    i2++;
                }
                return;
            }
            while (i2 < zzkn.size()) {
                this.zza.zzb(i, zzkn.zzb(i2));
                i2++;
            }
        } else if (z) {
            this.zza.zzc(i, 2);
            int i5 = 0;
            for (int i6 = 0; i6 < list.size(); i6++) {
                i5 += zzjc.zzg(list.get(i6).longValue());
            }
            this.zza.zzc(i5);
            while (i2 < list.size()) {
                this.zza.zzb(list.get(i2).longValue());
                i2++;
            }
        } else {
            while (i2 < list.size()) {
                this.zza.zzb(i, list.get(i2).longValue());
                i2++;
            }
        }
    }
}
