package com.google.ads.interactivemedia.v3.internal;

import java.util.AbstractSet;
import java.util.Iterator;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzxv extends AbstractSet {
    final /* synthetic */ zzxy zza;

    zzxv(zzxy zzxy) {
        this.zza = zzxy;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(Object obj) {
        return this.zza.containsKey(obj);
    }

    public final Iterator iterator() {
        return new zzxu(this);
    }

    public final boolean remove(Object obj) {
        return this.zza.zzd(obj) != null;
    }

    public final int size() {
        return this.zza.zzb;
    }
}
