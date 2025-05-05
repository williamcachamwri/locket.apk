package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzo;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzhq implements zzo {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzhl zzb;

    public final String zza(String str) {
        Map map = (Map) this.zzb.zzc.get(this.zza);
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return (String) map.get(str);
    }

    zzhq(zzhl zzhl, String str) {
        this.zza = str;
        this.zzb = zzhl;
    }
}
