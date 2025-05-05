package com.google.android.gms.internal.pal;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
abstract class zzzp implements Iterator {
    zzzq zza;
    zzzq zzb = null;
    int zzc;
    final /* synthetic */ zzzr zzd;

    zzzp(zzzr zzzr) {
        this.zzd = zzzr;
        this.zza = zzzr.zze.zzd;
        this.zzc = zzzr.zzd;
    }

    public final boolean hasNext() {
        return this.zza != this.zzd.zze;
    }

    public final void remove() {
        zzzq zzzq = this.zzb;
        if (zzzq != null) {
            this.zzd.zze(zzzq, true);
            this.zzb = null;
            this.zzc = this.zzd.zzd;
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: package-private */
    public final zzzq zza() {
        zzzq zzzq = this.zza;
        zzzr zzzr = this.zzd;
        if (zzzq == zzzr.zze) {
            throw new NoSuchElementException();
        } else if (zzzr.zzd == this.zzc) {
            this.zza = zzzq.zzd;
            this.zzb = zzzq;
            return zzzq;
        } else {
            throw new ConcurrentModificationException();
        }
    }
}
