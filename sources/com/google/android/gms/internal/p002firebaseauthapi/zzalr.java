package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzalr {
    private static final zzalr zza = new zzalr();
    private final zzalu zzb = new zzakq();
    private final ConcurrentMap<Class<?>, zzalv<?>> zzc = new ConcurrentHashMap();

    public static zzalr zza() {
        return zza;
    }

    public final <T> zzalv<T> zza(Class<T> cls) {
        zzakb.zza(cls, "messageType");
        zzalv<T> zzalv = (zzalv) this.zzc.get(cls);
        if (zzalv != null) {
            return zzalv;
        }
        zzalv<T> zza2 = this.zzb.zza(cls);
        zzakb.zza(cls, "messageType");
        zzakb.zza(zza2, "schema");
        zzalv<T> putIfAbsent = this.zzc.putIfAbsent(cls, zza2);
        return putIfAbsent != null ? putIfAbsent : zza2;
    }

    public final <T> zzalv<T> zza(T t) {
        return zza(t.getClass());
    }

    private zzalr() {
    }
}
