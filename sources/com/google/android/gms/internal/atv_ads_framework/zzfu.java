package com.google.android.gms.internal.atv_ads_framework;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzfu extends AbstractList implements RandomAccess, zzdw {
    /* access modifiers changed from: private */
    public final zzdw zza;

    public zzfu(zzdw zzdw) {
        this.zza = zzdw;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzdv) this.zza).get(i);
    }

    public final Iterator iterator() {
        return new zzft(this);
    }

    public final ListIterator listIterator(int i) {
        return new zzfs(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzdw zzd() {
        return this;
    }

    public final Object zze(int i) {
        return this.zza.zze(i);
    }

    public final List zzh() {
        return this.zza.zzh();
    }
}
