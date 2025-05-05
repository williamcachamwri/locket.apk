package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzpa {
    private final Class zza;
    private final Map zzb;
    private final Class zzc;

    @SafeVarargs
    protected zzpa(Class cls, zzpq... zzpqArr) {
        this.zza = cls;
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i <= 0) {
            zzpq zzpq = zzpqArr[i];
            if (!hashMap.containsKey(zzpq.zzb())) {
                hashMap.put(zzpq.zzb(), zzpq);
                i++;
            } else {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive ".concat(String.valueOf(zzpq.zzb().getCanonicalName())));
            }
        }
        this.zzc = zzpqArr[0].zzb();
        this.zzb = Collections.unmodifiableMap(hashMap);
    }

    public zzoz zza() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract zzvn zzb();

    public abstract zzaef zzc(zzaby zzaby) throws zzadi;

    public abstract String zzd();

    public abstract void zze(zzaef zzaef) throws GeneralSecurityException;

    public int zzf() {
        return 1;
    }

    public final Class zzi() {
        return this.zzc;
    }

    public final Class zzj() {
        return this.zza;
    }

    public final Object zzk(zzaef zzaef, Class cls) throws GeneralSecurityException {
        zzpq zzpq = (zzpq) this.zzb.get(cls);
        if (zzpq != null) {
            return zzpq.zza(zzaef);
        }
        String canonicalName = cls.getCanonicalName();
        throw new IllegalArgumentException("Requested primitive class " + canonicalName + " not supported.");
    }

    public final Set zzl() {
        return this.zzb.keySet();
    }
}
