package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzke implements zzkh {
    final /* synthetic */ zzkb zza;

    zzke(zzkb zzkb) {
        this.zza = zzkb;
    }

    public final zzkb zza(Class cls) throws GeneralSecurityException {
        if (this.zza.zzc().equals(cls)) {
            return this.zza;
        }
        throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
    }

    public final zzkb zzb() {
        return this.zza;
    }

    public final Class zzc() {
        return this.zza.getClass();
    }

    public final Class zzd() {
        return null;
    }

    public final Set zze() {
        return Collections.singleton(this.zza.zzc());
    }
}
