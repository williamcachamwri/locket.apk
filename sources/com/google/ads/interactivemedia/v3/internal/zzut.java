package com.google.ads.interactivemedia.v3.internal;

import java.util.logging.Logger;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzut {
    private final zzqp zza = new zzqp();
    private final String zzb;
    private volatile Logger zzc;

    zzut(Class cls) {
        this.zzb = cls.getName();
    }

    /* access modifiers changed from: package-private */
    public final Logger zza() {
        Logger logger = this.zzc;
        if (logger != null) {
            return logger;
        }
        synchronized (this.zza) {
            Logger logger2 = this.zzc;
            if (logger2 != null) {
                return logger2;
            }
            Logger logger3 = Logger.getLogger(this.zzb);
            this.zzc = logger3;
            return logger3;
        }
    }
}
