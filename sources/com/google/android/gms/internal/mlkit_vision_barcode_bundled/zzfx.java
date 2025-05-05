package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzfx {
    private static final zzfx zza = new zzfx();
    private final zzgi zzb = new zzfg();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzfx() {
    }

    public static zzfx zza() {
        return zza;
    }

    public final zzgh zzb(Class cls) {
        zzem.zzc(cls, "messageType");
        zzgh zzgh = (zzgh) this.zzc.get(cls);
        if (zzgh == null) {
            zzgh = this.zzb.zza(cls);
            zzem.zzc(cls, "messageType");
            zzem.zzc(zzgh, "schema");
            zzgh zzgh2 = (zzgh) this.zzc.putIfAbsent(cls, zzgh);
            return zzgh2 == null ? zzgh : zzgh2;
        }
    }
}
