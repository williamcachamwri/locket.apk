package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzqz extends AbstractMap implements Serializable, zzqs {
    private final zzrf zza;
    private transient Set zzb;

    zzqz(zzrf zzrf) {
        this.zza = zzrf;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return this.zza.containsValue(obj);
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        return this.zza.containsKey(obj);
    }

    public final Set entrySet() {
        Set set = this.zzb;
        if (set != null) {
            return set;
        }
        zzra zzra = new zzra(this.zza);
        this.zzb = zzra;
        return zzra;
    }

    @CheckForNull
    public final Object get(@CheckForNull Object obj) {
        int zzc = zzrg.zzc(obj);
        zzrf zzrf = this.zza;
        int zzd = zzrf.zzd(obj, zzc);
        if (zzd == -1) {
            return null;
        }
        return zzrf.zza[zzd];
    }

    public final Set keySet() {
        return this.zza.values();
    }

    @CheckForNull
    public final Object put(Object obj, Object obj2) {
        return this.zza.zzh(obj, obj2, false);
    }

    @CheckForNull
    public final Object remove(@CheckForNull Object obj) {
        int zzc = zzrg.zzc(obj);
        zzrf zzrf = this.zza;
        int zzd = zzrf.zzd(obj, zzc);
        if (zzd == -1) {
            return null;
        }
        Object obj2 = zzrf.zza[zzd];
        zzrf.zzm(zzd, zzc);
        return obj2;
    }

    public final int size() {
        return this.zza.zzc;
    }

    public final /* synthetic */ Collection values() {
        return this.zza.keySet();
    }
}
