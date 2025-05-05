package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzxb {
    private static zzxb zza;

    private zzxb() {
    }

    public static synchronized zzxb zza() {
        zzxb zzxb;
        synchronized (zzxb.class) {
            if (zza == null) {
                zza = new zzxb();
            }
            zzxb = zza;
        }
        return zzxb;
    }
}
