package com.google.ads.interactivemedia.v3.internal;

import java.util.AbstractMap;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzrs extends zzrm {
    final /* synthetic */ zzrt zza;

    zzrs(zzrt zzrt) {
        this.zza = zzrt;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return new AbstractMap.SimpleImmutableEntry(this.zza.zza.zzb.zzd.get(i), this.zza.zza.zzc.get(i));
    }

    public final int size() {
        return this.zza.zza.size();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzf() {
        return true;
    }
}
