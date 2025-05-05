package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzx extends zzs {
    private final transient zzr zza;
    private final transient zzp zzb;

    zzx(zzr zzr, zzp zzp) {
        this.zza = zzr;
        this.zzb = zzp;
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

    public final zzab zzd() {
        return this.zzb.listIterator(0);
    }
}
