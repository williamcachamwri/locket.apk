package com.google.ads.interactivemedia.v3.internal;

import java.util.AbstractSet;
import java.util.Iterator;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzre extends AbstractSet {
    final zzrf zzb;

    zzre(zzrf zzrf) {
        this.zzb = zzrf;
    }

    public final void clear() {
        this.zzb.clear();
    }

    public final Iterator iterator() {
        return new zzrd(this);
    }

    public final int size() {
        return this.zzb.zzc;
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(int i);
}
