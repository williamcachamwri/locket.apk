package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzqf implements Serializable {
    zzqf() {
    }

    public static zzqf zzf() {
        return zzpv.zza;
    }

    public static zzqf zzg(@CheckForNull Object obj) {
        return obj == null ? zzpv.zza : new zzqi(obj);
    }

    public static zzqf zzh(Object obj) {
        obj.getClass();
        return new zzqi(obj);
    }

    public abstract boolean equals(@CheckForNull Object obj);

    public abstract int hashCode();

    public abstract zzqf zza(zzpz zzpz);

    public abstract Object zzb();

    public abstract Object zzc(Object obj);

    @CheckForNull
    public abstract Object zzd();

    public abstract boolean zze();
}
