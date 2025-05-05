package com.google.android.gms.internal.atv_ads_framework;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzce extends zzcg {
    final /* synthetic */ zzcn zza;
    private int zzb = 0;
    private final int zzc;

    zzce(zzcn zzcn) {
        this.zza = zzcn;
        this.zzc = zzcn.zzd();
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
