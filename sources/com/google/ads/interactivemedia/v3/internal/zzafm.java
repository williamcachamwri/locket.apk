package com.google.ads.interactivemedia.v3.internal;

import java.util.NoSuchElementException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzafm extends zzacq {
    final zzafq zza;
    zzacs zzb = zzb();
    final /* synthetic */ zzafs zzc;

    zzafm(zzafs zzafs) {
        this.zzc = zzafs;
        this.zza = new zzafq(zzafs, (zzafp) null);
    }

    private final zzacs zzb() {
        zzafq zzafq = this.zza;
        if (zzafq.hasNext()) {
            return zzafq.next().iterator();
        }
        return null;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final byte zza() {
        zzacs zzacs = this.zzb;
        if (zzacs != null) {
            byte zza2 = zzacs.zza();
            if (!this.zzb.hasNext()) {
                this.zzb = zzb();
            }
            return zza2;
        }
        throw new NoSuchElementException();
    }
}
