package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzes {
    private final zzbc zza = new zzbc();
    private final String zzb;
    private volatile Logger zzc;

    zzes(Class cls) {
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
