package com.google.ads.interactivemedia.v3.internal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzxw implements Iterator {
    zzxx zza;
    zzxx zzb = null;
    int zzc;
    final /* synthetic */ zzxy zzd;

    zzxw(zzxy zzxy) {
        this.zzd = zzxy;
        this.zza = zzxy.zzd.zzd;
        this.zzc = zzxy.zzc;
    }

    public final boolean hasNext() {
        return this.zza != this.zzd.zzd;
    }

    public final void remove() {
        zzxx zzxx = this.zzb;
        if (zzxx != null) {
            this.zzd.zze(zzxx, true);
            this.zzb = null;
            this.zzc = this.zzd.zzc;
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: package-private */
    public final zzxx zza() {
        zzxy zzxy = this.zzd;
        zzxx zzxx = this.zza;
        if (zzxx == zzxy.zzd) {
            throw new NoSuchElementException();
        } else if (zzxy.zzc == this.zzc) {
            this.zza = zzxx.zzd;
            this.zzb = zzxx;
            return zzxx;
        } else {
            throw new ConcurrentModificationException();
        }
    }
}
