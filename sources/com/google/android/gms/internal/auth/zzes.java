package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzes implements zzfv {
    private static final zzes zza = new zzes();

    private zzes() {
    }

    public static zzes zza() {
        return zza;
    }

    public final zzfu zzb(Class cls) {
        if (zzev.class.isAssignableFrom(cls)) {
            try {
                return (zzfu) zzev.zzb(cls.asSubclass(zzev.class)).zzn(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public final boolean zzc(Class cls) {
        return zzev.class.isAssignableFrom(cls);
    }
}
