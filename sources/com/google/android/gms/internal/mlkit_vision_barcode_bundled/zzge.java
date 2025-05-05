package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzge implements Iterator {
    private final ArrayDeque zza;
    private zzcx zzb;

    /* synthetic */ zzge(zzdb zzdb, zzgd zzgd) {
        if (zzdb instanceof zzgg) {
            zzgg zzgg = (zzgg) zzdb;
            ArrayDeque arrayDeque = new ArrayDeque(zzgg.zzf());
            this.zza = arrayDeque;
            arrayDeque.push(zzgg);
            this.zzb = zzb(zzgg.zzd);
            return;
        }
        this.zza = null;
        this.zzb = (zzcx) zzdb;
    }

    private final zzcx zzb(zzdb zzdb) {
        while (zzdb instanceof zzgg) {
            zzgg zzgg = (zzgg) zzdb;
            this.zza.push(zzgg);
            zzdb = zzgg.zzd;
        }
        return (zzcx) zzdb;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: zza */
    public final zzcx next() {
        zzcx zzcx;
        zzcx zzcx2 = this.zzb;
        if (zzcx2 != null) {
            do {
                ArrayDeque arrayDeque = this.zza;
                zzcx = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    this.zzb = zzcx;
                } else {
                    zzcx = zzb(((zzgg) this.zza.pop()).zze);
                }
            } while (zzcx.zzd() == 0);
            this.zzb = zzcx;
            return zzcx2;
        }
        throw new NoSuchElementException();
    }
}
