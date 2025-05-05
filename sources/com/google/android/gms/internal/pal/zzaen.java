package com.google.android.gms.internal.pal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaen {
    private static final zzaen zza = new zzaen();
    private final zzaes zzb = new zzadx();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzaen() {
    }

    public static zzaen zza() {
        return zza;
    }

    public final zzaer zzb(Class cls) {
        zzadg.zzf(cls, "messageType");
        zzaer zzaer = (zzaer) this.zzc.get(cls);
        if (zzaer == null) {
            zzaer = this.zzb.zza(cls);
            zzadg.zzf(cls, "messageType");
            zzadg.zzf(zzaer, "schema");
            zzaer zzaer2 = (zzaer) this.zzc.putIfAbsent(cls, zzaer);
            return zzaer2 == null ? zzaer : zzaer2;
        }
    }
}
