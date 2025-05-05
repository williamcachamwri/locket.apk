package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzgl {
    private static zzgk zza;

    public static synchronized zzgk zza() {
        zzgk zzgk;
        synchronized (zzgl.class) {
            if (zza == null) {
                zza(new zzgn());
            }
            zzgk = zza;
        }
        return zzgk;
    }

    private static synchronized void zza(zzgk zzgk) {
        synchronized (zzgl.class) {
            if (zza == null) {
                zza = zzgk;
            } else {
                throw new IllegalStateException("init() already called");
            }
        }
    }
}
