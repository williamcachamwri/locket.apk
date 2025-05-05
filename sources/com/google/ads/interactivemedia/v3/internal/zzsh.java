package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzsh extends zzrr {
    private final transient zzrp zza;
    private final transient zzrm zzb;

    zzsh(zzrp zzrp, zzrm zzrm) {
        this.zza = zzrp;
        this.zzb = zzrm;
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

    public final zzrm zzd() {
        return this.zzb;
    }

    public final zzss zze() {
        return this.zzb.listIterator(0);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return true;
    }
}
