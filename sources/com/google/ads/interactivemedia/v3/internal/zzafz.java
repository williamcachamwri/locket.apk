package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzafz implements Iterator {
    final /* synthetic */ zzagd zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* synthetic */ zzafz(zzagd zzagd, zzafy zzafy) {
        this.zza = zzagd;
    }

    private final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    public final boolean hasNext() {
        int i = this.zzb + 1;
        zzagd zzagd = this.zza;
        if (i < zzagd.zzb) {
            return true;
        }
        if (!zzagd.zzc.isEmpty()) {
            return zza().hasNext();
        }
        return false;
    }

    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        zzagd zzagd = this.zza;
        if (i < zzagd.zzb) {
            return (zzafx) zzagd.zza[i];
        }
        return (Map.Entry) zza().next();
    }

    public final void remove() {
        if (this.zzc) {
            this.zzc = false;
            this.zza.zzo();
            int i = this.zzb;
            zzagd zzagd = this.zza;
            if (i < zzagd.zzb) {
                this.zzb = i - 1;
                Object unused = zzagd.zzm(i);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
