package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Arrays;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzgc {
    private final ArrayDeque zza = new ArrayDeque();

    private zzgc() {
    }

    static /* bridge */ /* synthetic */ zzdb zza(zzgc zzgc, zzdb zzdb, zzdb zzdb2) {
        zzgc.zzb(zzdb);
        zzgc.zzb(zzdb2);
        zzdb zzdb3 = (zzdb) zzgc.zza.pop();
        while (!zzgc.zza.isEmpty()) {
            zzdb3 = new zzgg((zzdb) zzgc.zza.pop(), zzdb3);
        }
        return zzdb3;
    }

    private final void zzb(zzdb zzdb) {
        if (zzdb.zzh()) {
            int zzc = zzc(zzdb.zzd());
            int zzc2 = zzgg.zzc(zzc + 1);
            if (this.zza.isEmpty() || ((zzdb) this.zza.peek()).zzd() >= zzc2) {
                this.zza.push(zzdb);
                return;
            }
            int zzc3 = zzgg.zzc(zzc);
            zzdb zzdb2 = (zzdb) this.zza.pop();
            while (!this.zza.isEmpty() && ((zzdb) this.zza.peek()).zzd() < zzc3) {
                zzdb2 = new zzgg((zzdb) this.zza.pop(), zzdb2);
            }
            zzgg zzgg = new zzgg(zzdb2, zzdb);
            while (!this.zza.isEmpty() && ((zzdb) this.zza.peek()).zzd() < zzgg.zzc(zzc(zzgg.zzd()) + 1)) {
                zzgg = new zzgg((zzdb) this.zza.pop(), zzgg);
            }
            this.zza.push(zzgg);
        } else if (zzdb instanceof zzgg) {
            zzgg zzgg2 = (zzgg) zzdb;
            zzb(zzgg2.zzd);
            zzb(zzgg2.zze);
        } else {
            throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(String.valueOf(zzdb.getClass()))));
        }
    }

    private static final int zzc(int i) {
        int binarySearch = Arrays.binarySearch(zzgg.zza, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    /* synthetic */ zzgc(zzgb zzgb) {
    }
}
