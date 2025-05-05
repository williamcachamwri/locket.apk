package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayDeque;
import java.util.Arrays;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzafo {
    private final ArrayDeque zza = new ArrayDeque();

    private zzafo() {
    }

    static /* bridge */ /* synthetic */ zzacw zza(zzafo zzafo, zzacw zzacw, zzacw zzacw2) {
        zzafo.zzb(zzacw);
        zzafo.zzb(zzacw2);
        zzacw zzacw3 = (zzacw) zzafo.zza.pop();
        while (!zzafo.zza.isEmpty()) {
            zzacw3 = new zzafs((zzacw) zzafo.zza.pop(), zzacw3);
        }
        return zzacw3;
    }

    private final void zzb(zzacw zzacw) {
        if (zzacw.zzh()) {
            int zzc = zzc(zzacw.zzd());
            ArrayDeque arrayDeque = this.zza;
            int zzc2 = zzafs.zzc(zzc + 1);
            if (arrayDeque.isEmpty() || ((zzacw) this.zza.peek()).zzd() >= zzc2) {
                this.zza.push(zzacw);
                return;
            }
            int zzc3 = zzafs.zzc(zzc);
            zzacw zzacw2 = (zzacw) this.zza.pop();
            while (!this.zza.isEmpty() && ((zzacw) this.zza.peek()).zzd() < zzc3) {
                zzacw2 = new zzafs((zzacw) this.zza.pop(), zzacw2);
            }
            zzafs zzafs = new zzafs(zzacw2, zzacw);
            while (!this.zza.isEmpty()) {
                ArrayDeque arrayDeque2 = this.zza;
                if (((zzacw) arrayDeque2.peek()).zzd() >= zzafs.zzc(zzc(zzafs.zzd()) + 1)) {
                    break;
                }
                zzafs = new zzafs((zzacw) this.zza.pop(), zzafs);
            }
            this.zza.push(zzafs);
        } else if (zzacw instanceof zzafs) {
            zzafs zzafs2 = (zzafs) zzacw;
            zzb(zzafs2.zzd);
            zzb(zzafs2.zze);
        } else {
            throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(String.valueOf(zzacw.getClass()))));
        }
    }

    private static final int zzc(int i) {
        int binarySearch = Arrays.binarySearch(zzafs.zza, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    /* synthetic */ zzafo(zzafn zzafn) {
    }
}
