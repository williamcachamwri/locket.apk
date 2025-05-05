package com.google.ads.interactivemedia.v3.internal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzxt extends AbstractSet {
    final /* synthetic */ zzxy zza;

    zzxt(zzxy zzxy) {
        this.zza = zzxy;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(Object obj) {
        return (obj instanceof Map.Entry) && this.zza.zzb((Map.Entry) obj) != null;
    }

    public final Iterator iterator() {
        return new zzxs(this);
    }

    public final boolean remove(Object obj) {
        zzxx zzb;
        if (!(obj instanceof Map.Entry) || (zzb = this.zza.zzb((Map.Entry) obj)) == null) {
            return false;
        }
        this.zza.zze(zzb, true);
        return true;
    }

    public final int size() {
        return this.zza.zzb;
    }
}
