package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzma extends zzmb {
    private final /* synthetic */ zzlv zza;

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzlx(this.zza);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzma(zzlv zzlv) {
        super(zzlv);
        this.zza = zzlv;
    }
}
