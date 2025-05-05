package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzoz {
    private final Class zza;

    public zzoz(Class cls) {
        this.zza = cls;
    }

    public abstract zzaef zza(zzaef zzaef) throws GeneralSecurityException;

    public abstract zzaef zzb(zzaby zzaby) throws zzadi;

    public Map zzc() throws GeneralSecurityException {
        return Collections.emptyMap();
    }

    public abstract void zzd(zzaef zzaef) throws GeneralSecurityException;

    public final Class zzg() {
        return this.zza;
    }
}
