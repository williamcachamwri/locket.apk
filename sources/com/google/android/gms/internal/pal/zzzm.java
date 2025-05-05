package com.google.android.gms.internal.pal;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzzm extends AbstractSet {
    final /* synthetic */ zzzr zza;

    zzzm(zzzr zzzr) {
        this.zza = zzzr;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(Object obj) {
        return (obj instanceof Map.Entry) && this.zza.zzb((Map.Entry) obj) != null;
    }

    public final Iterator iterator() {
        return new zzzl(this);
    }

    public final boolean remove(Object obj) {
        zzzq zzb;
        if (!(obj instanceof Map.Entry) || (zzb = this.zza.zzb((Map.Entry) obj)) == null) {
            return false;
        }
        this.zza.zze(zzb, true);
        return true;
    }

    public final int size() {
        return this.zza.zzc;
    }
}
