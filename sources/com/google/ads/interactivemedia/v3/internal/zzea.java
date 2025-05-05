package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzea extends zzeb {
    protected final HashSet zza;
    protected final JSONObject zzb;
    protected final long zzc;

    public zzea(zzdt zzdt, HashSet hashSet, JSONObject jSONObject, long j) {
        super(zzdt);
        this.zza = new HashSet(hashSet);
        this.zzb = jSONObject;
        this.zzc = j;
    }
}
