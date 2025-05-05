package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzlq {
    private static final zzlq zza = new zzlq();
    private final zzlt zzb = new zzkq();
    private final ConcurrentMap<Class<?>, zzlu<?>> zzc = new ConcurrentHashMap();

    public static zzlq zza() {
        return zza;
    }

    public final <T> zzlu<T> zza(Class<T> cls) {
        zzjv.zza(cls, "messageType");
        zzlu<T> zzlu = (zzlu) this.zzc.get(cls);
        if (zzlu != null) {
            return zzlu;
        }
        zzlu<T> zza2 = this.zzb.zza(cls);
        zzjv.zza(cls, "messageType");
        zzjv.zza(zza2, "schema");
        zzlu<T> putIfAbsent = this.zzc.putIfAbsent(cls, zza2);
        return putIfAbsent != null ? putIfAbsent : zza2;
    }

    public final <T> zzlu<T> zza(T t) {
        return zza(t.getClass());
    }

    private zzlq() {
    }
}
