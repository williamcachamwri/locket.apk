package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzdw implements zzfm {
    private static final zzdw zza = new zzdw();

    private zzdw() {
    }

    public static zzdw zza() {
        return zza;
    }

    public final zzfl zzb(Class cls) {
        if (zzed.class.isAssignableFrom(cls)) {
            try {
                return (zzfl) zzed.zzI(cls.asSubclass(zzed.class)).zzg(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
    }

    public final boolean zzc(Class cls) {
        return zzed.class.isAssignableFrom(cls);
    }
}
