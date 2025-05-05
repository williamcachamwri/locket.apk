package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzfo implements zzfv {
    private final zzfv[] zza;

    zzfo(zzfv... zzfvArr) {
        this.zza = zzfvArr;
    }

    public final zzfu zzb(Class cls) {
        zzfv[] zzfvArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzfv zzfv = zzfvArr[i];
            if (zzfv.zzc(cls)) {
                return zzfv.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        zzfv[] zzfvArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzfvArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
