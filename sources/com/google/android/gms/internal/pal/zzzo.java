package com.google.android.gms.internal.pal;

import java.util.AbstractSet;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzzo extends AbstractSet {
    final /* synthetic */ zzzr zza;

    zzzo(zzzr zzzr) {
        this.zza = zzzr;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(Object obj) {
        return this.zza.containsKey(obj);
    }

    public final Iterator iterator() {
        return new zzzn(this);
    }

    public final boolean remove(Object obj) {
        return this.zza.zzd(obj) != null;
    }

    public final int size() {
        return this.zza.zzc;
    }
}
