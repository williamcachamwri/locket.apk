package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzac {
    private zzad zza;
    private zzad zzb;
    private List<zzad> zzc;

    public final zzad zza() {
        return this.zza;
    }

    public final zzad zzb() {
        return this.zzb;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzac zzac = new zzac((zzad) this.zza.clone());
        for (zzad clone : this.zzc) {
            zzac.zzc.add((zzad) clone.clone());
        }
        return zzac;
    }

    public final List<zzad> zzc() {
        return this.zzc;
    }

    public zzac() {
        this.zza = new zzad("", 0, (Map<String, Object>) null);
        this.zzb = new zzad("", 0, (Map<String, Object>) null);
        this.zzc = new ArrayList();
    }

    private zzac(zzad zzad) {
        this.zza = zzad;
        this.zzb = (zzad) zzad.clone();
        this.zzc = new ArrayList();
    }

    public final void zza(zzad zzad) {
        this.zza = zzad;
        this.zzb = (zzad) zzad.clone();
        this.zzc.clear();
    }

    public final void zza(String str, long j, Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        for (String next : map.keySet()) {
            hashMap.put(next, zzad.zza(next, this.zza.zza(next), map.get(next)));
        }
        this.zzc.add(new zzad(str, j, hashMap));
    }

    public final void zzb(zzad zzad) {
        this.zzb = zzad;
    }
}
