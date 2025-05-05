package com.google.android.gms.internal.pal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzyx extends zzyy implements Iterable {
    private final List zza = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzyx) {
            return ((zzyx) obj).zza.equals(this.zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator iterator() {
        return this.zza.iterator();
    }

    public final int zza() {
        if (this.zza.size() == 1) {
            return ((zzyy) this.zza.get(0)).zza();
        }
        throw new IllegalStateException();
    }

    public final int zzb() {
        return this.zza.size();
    }

    public final zzyy zzc(int i) {
        return (zzyy) this.zza.get(i);
    }

    public final String zzd() {
        if (this.zza.size() == 1) {
            return ((zzyy) this.zza.get(0)).zzd();
        }
        throw new IllegalStateException();
    }

    public final void zze(zzyy zzyy) {
        this.zza.add(zzyy);
    }
}
