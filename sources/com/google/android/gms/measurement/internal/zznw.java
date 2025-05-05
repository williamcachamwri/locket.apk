package com.google.android.gms.measurement.internal;

import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zznw {
    private String zza;
    private Map<String, String> zzb;
    private zznt zzc;

    public final zznt zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zza;
    }

    public final Map<String, String> zzc() {
        Map<String, String> map = this.zzb;
        return map == null ? Collections.emptyMap() : map;
    }

    zznw(String str, zznt zznt) {
        this.zza = str;
        this.zzc = zznt;
    }

    zznw(String str, Map<String, String> map, zznt zznt) {
        this.zza = str;
        this.zzb = map;
        this.zzc = zznt;
    }
}
