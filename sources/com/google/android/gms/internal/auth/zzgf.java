package com.google.android.gms.internal.auth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzgf {
    private static final zzgf zza = new zzgf();
    private final zzgj zzb = new zzfp();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzgf() {
    }

    public static zzgf zza() {
        return zza;
    }

    public final zzgi zzb(Class cls) {
        zzfa.zzc(cls, "messageType");
        zzgi zzgi = (zzgi) this.zzc.get(cls);
        if (zzgi == null) {
            zzgi = this.zzb.zza(cls);
            zzfa.zzc(cls, "messageType");
            zzgi zzgi2 = (zzgi) this.zzc.putIfAbsent(cls, zzgi);
            return zzgi2 == null ? zzgi : zzgi2;
        }
    }
}
