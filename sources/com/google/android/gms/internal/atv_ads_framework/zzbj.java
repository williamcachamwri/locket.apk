package com.google.android.gms.internal.atv_ads_framework;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzbj extends zzbu {
    boolean zza;
    final /* synthetic */ Object zzb;

    zzbj(Object obj) {
        this.zzb = obj;
    }

    public final boolean hasNext() {
        return !this.zza;
    }

    public final Object next() {
        if (!this.zza) {
            this.zza = true;
            return this.zzb;
        }
        throw new NoSuchElementException();
    }
}
