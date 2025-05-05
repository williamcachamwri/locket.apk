package com.google.ads.interactivemedia.v3.internal;

import java.util.NoSuchElementException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzacp extends zzacq {
    final /* synthetic */ zzacw zza;
    private int zzb = 0;
    private final int zzc;

    zzacp(zzacw zzacw) {
        this.zza = zzacw;
        this.zzc = zzacw.zzd();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
