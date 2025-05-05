package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzafq implements Iterator {
    private final ArrayDeque zza;
    private zzacu zzb;

    /* synthetic */ zzafq(zzacw zzacw, zzafp zzafp) {
        if (zzacw instanceof zzafs) {
            zzafs zzafs = (zzafs) zzacw;
            ArrayDeque arrayDeque = new ArrayDeque(zzafs.zzf());
            this.zza = arrayDeque;
            arrayDeque.push(zzafs);
            this.zzb = zzb(zzafs.zzd);
            return;
        }
        this.zza = null;
        this.zzb = (zzacu) zzacw;
    }

    private final zzacu zzb(zzacw zzacw) {
        while (zzacw instanceof zzafs) {
            zzafs zzafs = (zzafs) zzacw;
            this.zza.push(zzafs);
            zzacw = zzafs.zzd;
        }
        return (zzacu) zzacw;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: zza */
    public final zzacu next() {
        zzacu zzacu;
        zzacu zzacu2 = this.zzb;
        if (zzacu2 != null) {
            do {
                ArrayDeque arrayDeque = this.zza;
                zzacu = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    this.zzb = zzacu;
                } else {
                    zzacu = zzb(((zzafs) this.zza.pop()).zze);
                }
            } while (zzacu.zzd() == 0);
            this.zzb = zzacu;
            return zzacu2;
        }
        throw new NoSuchElementException();
    }
}
