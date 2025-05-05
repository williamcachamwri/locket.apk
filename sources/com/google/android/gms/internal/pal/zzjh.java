package com.google.android.gms.internal.pal;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzjh extends zzjd {
    private final transient zzjc zza;
    private final transient zziz zzb;

    zzjh(zzjc zzjc, zziz zziz) {
        this.zza = zzjc;
        this.zzb = zziz;
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

    public final zzjl zzd() {
        return this.zzb.listIterator(0);
    }
}
