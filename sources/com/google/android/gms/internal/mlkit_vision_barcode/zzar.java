package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.SystemClock;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzar {
    private static final zzbb zza;

    static {
        zzbb zzbb;
        try {
            SystemClock.elapsedRealtimeNanos();
            zzbb = new zzap();
        } catch (Throwable unused) {
            SystemClock.elapsedRealtime();
            zzbb = new zzaq();
        }
        zza = zzbb;
    }

    public static zzbb zza() {
        return zza;
    }
}
