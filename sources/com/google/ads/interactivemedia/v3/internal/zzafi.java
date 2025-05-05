package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzafi {
    public static final /* synthetic */ int zza = 0;
    private static final zzafi zzb = new zzafi();
    private final zzafu zzc = new zzaet();
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    private zzafi() {
    }

    public static zzafi zza() {
        return zzb;
    }

    public final zzaft zzb(Class cls) {
        zzaee.zzc(cls, "messageType");
        zzaft zzaft = (zzaft) this.zzd.get(cls);
        if (zzaft == null) {
            zzaft = this.zzc.zza(cls);
            zzaee.zzc(cls, "messageType");
            zzaft zzaft2 = (zzaft) this.zzd.putIfAbsent(cls, zzaft);
            return zzaft2 == null ? zzaft : zzaft2;
        }
    }
}
