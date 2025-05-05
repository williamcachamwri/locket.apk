package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzde implements zzem {
    private static final zzde zza = new zzde();

    private zzde() {
    }

    public static zzde zza() {
        return zza;
    }

    public final zzel zzb(Class cls) {
        if (zzdh.class.isAssignableFrom(cls)) {
            try {
                return (zzel) zzdh.zzp(cls.asSubclass(zzdh.class)).zze(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public final boolean zzc(Class cls) {
        return zzdh.class.isAssignableFrom(cls);
    }
}
