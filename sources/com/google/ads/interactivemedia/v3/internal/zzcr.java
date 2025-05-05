package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.omid.library.adsession.zze;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcr {
    private static final zzcr zza = new zzcr();
    private final ArrayList zzb = new ArrayList();
    private final ArrayList zzc = new ArrayList();

    private zzcr() {
    }

    public static zzcr zza() {
        return zza;
    }

    public final Collection zzb() {
        return Collections.unmodifiableCollection(this.zzc);
    }

    public final Collection zzc() {
        return Collections.unmodifiableCollection(this.zzb);
    }

    public final void zzd(zze zze) {
        this.zzb.add(zze);
    }

    public final void zze(zze zze) {
        ArrayList arrayList = this.zzb;
        boolean zzg = zzg();
        arrayList.remove(zze);
        this.zzc.remove(zze);
        if (zzg && !zzg()) {
            zzcz.zzb().zzg();
        }
    }

    public final void zzf(zze zze) {
        ArrayList arrayList = this.zzc;
        boolean zzg = zzg();
        arrayList.add(zze);
        if (!zzg) {
            zzcz.zzb().zzf();
        }
    }

    public final boolean zzg() {
        return this.zzc.size() > 0;
    }
}
