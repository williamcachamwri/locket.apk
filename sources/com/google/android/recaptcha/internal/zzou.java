package com.google.android.recaptcha.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzou {
    public static final /* synthetic */ int zza = 0;
    private static final zzou zzb = new zzou();
    private final zzoz zzc = new zzoc();
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    private zzou() {
    }

    public static zzou zza() {
        return zzb;
    }

    public final zzoy zzb(Class cls) {
        zznn.zzc(cls, "messageType");
        zzoy zzoy = (zzoy) this.zzd.get(cls);
        if (zzoy == null) {
            zzoy = this.zzc.zza(cls);
            zznn.zzc(cls, "messageType");
            zzoy zzoy2 = (zzoy) this.zzd.putIfAbsent(cls, zzoy);
            return zzoy2 == null ? zzoy : zzoy2;
        }
    }
}
