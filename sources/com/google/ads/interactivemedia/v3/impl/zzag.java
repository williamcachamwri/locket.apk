package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdProgressInfo;
import com.google.ads.interactivemedia.v3.impl.data.zzc;
import com.google.ads.interactivemedia.v3.internal.zzaho;
import com.google.ads.interactivemedia.v3.internal.zzahr;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzag {
    public final AdEvent.AdEventType zza;
    public final zzc zzb;
    public Map zzc;
    public List zzd = new ArrayList();
    AdProgressInfo zze;
    public List zzf;
    public double zzg;

    public zzag(AdEvent.AdEventType adEventType, zzc zzc2) {
        this.zza = adEventType;
        this.zzb = zzc2;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return zzaho.zzf(this, obj, false, (Class) null, false, new String[0]);
    }

    public final int hashCode() {
        return zzahr.zza(this, new String[0]);
    }
}
