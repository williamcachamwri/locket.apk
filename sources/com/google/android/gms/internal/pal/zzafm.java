package com.google.android.gms.internal.pal;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzafm implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzafn zzb;

    zzafm(zzafn zzafn) {
        this.zzb = zzafn;
        this.zza = zzafn.zza.iterator();
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
