package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzkg implements zzkh {
    final /* synthetic */ zzpr zza;
    final /* synthetic */ zzpa zzb;

    zzkg(zzpr zzpr, zzpa zzpa) {
        this.zza = zzpr;
        this.zzb = zzpa;
    }

    public final zzkb zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzld(this.zza, this.zzb, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzkb zzb() {
        zzpr zzpr = this.zza;
        return new zzld(zzpr, this.zzb, zzpr.zzi());
    }

    public final Class zzc() {
        return this.zza.getClass();
    }

    public final Class zzd() {
        return this.zzb.getClass();
    }

    public final Set zze() {
        return this.zza.zzl();
    }
}
