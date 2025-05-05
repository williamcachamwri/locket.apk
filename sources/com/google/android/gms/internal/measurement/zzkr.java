package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzkr implements zzkz {
    private zzkz[] zza;

    public final zzla zza(Class<?> cls) {
        for (zzkz zzkz : this.zza) {
            if (zzkz.zzb(cls)) {
                return zzkz.zza(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: " + cls.getName());
    }

    zzkr(zzkz... zzkzArr) {
        this.zza = zzkzArr;
    }

    public final boolean zzb(Class<?> cls) {
        for (zzkz zzb : this.zza) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }
}
