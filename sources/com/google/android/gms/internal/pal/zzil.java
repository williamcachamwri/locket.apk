package com.google.android.gms.internal.pal;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzil implements Serializable {
    zzil() {
    }

    public static zzil zze() {
        return zzif.zza;
    }

    public static zzil zzf(Object obj) {
        obj.getClass();
        return new zziq(obj);
    }

    public abstract zzil zza(zzii zzii);

    public abstract Object zzb();

    public abstract Object zzc(Object obj);

    public abstract boolean zzd();
}
