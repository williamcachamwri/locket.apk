package com.google.android.gms.internal.atv_ads_framework;

import java.util.Iterator;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzft implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzfu zzb;

    zzft(zzfu zzfu) {
        this.zzb = zzfu;
        this.zza = zzfu.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
