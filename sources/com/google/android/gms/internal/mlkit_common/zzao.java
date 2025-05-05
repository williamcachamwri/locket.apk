package com.google.android.gms.internal.mlkit_common;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzao extends zzaj {
    private final transient zzai zza;
    private final transient zzaf zzb;

    zzao(zzai zzai, zzaf zzaf) {
        this.zza = zzai;
        this.zzb = zzaf;
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

    public final zzas zzd() {
        return this.zzb.listIterator(0);
    }
}
