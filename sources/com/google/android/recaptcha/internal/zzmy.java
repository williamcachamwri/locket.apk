package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzmy implements zzoi {
    private static final zzmy zza = new zzmy();

    private zzmy() {
    }

    public static zzmy zza() {
        return zza;
    }

    public final zzoh zzb(Class cls) {
        if (zznf.class.isAssignableFrom(cls)) {
            try {
                return (zzoh) zznf.zzu(cls.asSubclass(zznf.class)).zzh(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public final boolean zzc(Class cls) {
        return zznf.class.isAssignableFrom(cls);
    }
}
