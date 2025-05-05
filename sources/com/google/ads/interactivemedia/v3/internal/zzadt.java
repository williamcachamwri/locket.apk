package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzadt implements zzaez {
    private static final zzadt zza = new zzadt();

    private zzadt() {
    }

    public static zzadt zza() {
        return zza;
    }

    public final zzaey zzb(Class cls) {
        if (zzady.class.isAssignableFrom(cls)) {
            try {
                return (zzaey) zzady.zzaA(cls.asSubclass(zzady.class)).zzm(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public final boolean zzc(Class cls) {
        return zzady.class.isAssignableFrom(cls);
    }
}
