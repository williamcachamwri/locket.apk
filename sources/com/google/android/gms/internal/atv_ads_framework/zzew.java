package com.google.android.gms.internal.atv_ads_framework;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzew {
    private static final zzew zza = new zzew();
    private final zzez zzb = new zzeg();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzew() {
    }

    public static zzew zza() {
        return zza;
    }

    public final zzey zzb(Class cls) {
        zzdp.zzc(cls, "messageType");
        zzey zzey = (zzey) this.zzc.get(cls);
        if (zzey == null) {
            zzey = this.zzb.zza(cls);
            zzdp.zzc(cls, "messageType");
            zzdp.zzc(zzey, "schema");
            zzey zzey2 = (zzey) this.zzc.putIfAbsent(cls, zzey);
            return zzey2 == null ? zzey : zzey2;
        }
    }
}
