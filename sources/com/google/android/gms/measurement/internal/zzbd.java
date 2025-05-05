package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzbd implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzbe zzb;

    public final /* synthetic */ Object next() {
        return this.zza.next();
    }

    zzbd(zzbe zzbe) {
        this.zzb = zzbe;
        this.zza = zzbe.zza.keySet().iterator();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }
}
