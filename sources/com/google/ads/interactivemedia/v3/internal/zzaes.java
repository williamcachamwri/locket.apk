package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaes implements zzaez {
    private final zzaez[] zza;

    zzaes(zzaez... zzaezArr) {
        this.zza = zzaezArr;
    }

    public final zzaey zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zzaez zzaez = this.zza[i];
            if (zzaez.zzc(cls)) {
                return zzaez.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        for (int i = 0; i < 2; i++) {
            if (this.zza[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
