package com.google.android.gms.internal.auth;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzhd implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzhe zzb;

    zzhd(zzhe zzhe) {
        this.zzb = zzhe;
        this.zza = zzhe.zza.iterator();
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
