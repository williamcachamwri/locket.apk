package com.google.android.gms.internal.pal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzfh {
    private final Map zza = new HashMap();

    public final AtomicReference zza(String str) {
        synchronized (this) {
            if (!this.zza.containsKey(str)) {
                this.zza.put(str, new AtomicReference());
            }
        }
        return (AtomicReference) this.zza.get(str);
    }
}
