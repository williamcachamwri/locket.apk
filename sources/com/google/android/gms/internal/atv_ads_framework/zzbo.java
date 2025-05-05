package com.google.android.gms.internal.atv_ads_framework;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzbo extends zzbi {
    private final transient zzbh zza;
    private final transient zzbe zzb;

    zzbo(zzbh zzbh, zzbe zzbe) {
        this.zza = zzbh;
        this.zzb = zzbe;
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    public final int size() {
        return this.zza.size();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    public final zzbe zzd() {
        return this.zzb;
    }

    public final zzbu zze() {
        return this.zzb.listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        throw null;
    }
}
