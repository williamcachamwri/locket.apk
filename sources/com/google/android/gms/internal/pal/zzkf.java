package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzkf implements zzkh {
    final /* synthetic */ zzpa zza;

    zzkf(zzpa zzpa) {
        this.zza = zzpa;
    }

    public final zzkb zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzkd(this.zza, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzkb zzb() {
        zzpa zzpa = this.zza;
        return new zzkd(zzpa, zzpa.zzi());
    }

    public final Class zzc() {
        return this.zza.getClass();
    }

    public final Class zzd() {
        return null;
    }

    public final Set zze() {
        return this.zza.zzl();
    }
}
