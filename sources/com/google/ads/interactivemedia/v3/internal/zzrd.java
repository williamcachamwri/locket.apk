package com.google.ads.interactivemedia.v3.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzrd implements Iterator {
    final /* synthetic */ zzre zza;
    private int zzb;
    private int zzc = -1;
    private int zzd;
    private int zze;

    zzrd(zzre zzre) {
        this.zza = zzre;
        this.zzb = zzre.zzb.zzi;
        zzrf zzrf = zzre.zzb;
        this.zzd = zzrf.zzd;
        this.zze = zzrf.zzc;
    }

    private final void zza() {
        if (this.zza.zzb.zzd != this.zzd) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        zza();
        return this.zzb != -2 && this.zze > 0;
    }

    public final Object next() {
        if (hasNext()) {
            Object zza2 = this.zza.zza(this.zzb);
            this.zzc = this.zzb;
            this.zzb = this.zza.zzb.zzl[this.zzb];
            this.zze--;
            return zza2;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        zza();
        zzqh.zzi(this.zzc != -1, "no calls to next() since the last call to remove()");
        int i = this.zzc;
        zzrf zzrf = this.zza.zzb;
        zzrf.zzl(i, zzrg.zzc(zzrf.zza[i]));
        zzre zzre = this.zza;
        int i2 = this.zzb;
        zzrf zzrf2 = zzre.zzb;
        if (i2 == zzrf2.zzc) {
            this.zzb = this.zzc;
        }
        this.zzc = -1;
        this.zzd = zzrf2.zzd;
    }
}
