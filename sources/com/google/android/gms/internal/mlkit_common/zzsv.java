package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final class zzsv {
    private static zzsv zza;

    private zzsv() {
    }

    public static synchronized zzsv zza() {
        zzsv zzsv;
        synchronized (zzsv.class) {
            if (zza == null) {
                zza = new zzsv();
            }
            zzsv = zza;
        }
        return zzsv;
    }

    public static void zzb() {
        zzsu.zza();
    }
}
