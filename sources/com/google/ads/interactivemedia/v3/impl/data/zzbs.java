package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzaho;
import com.google.ads.interactivemedia.v3.internal.zzahr;
import com.google.ads.interactivemedia.v3.internal.zzqc;
import com.google.ads.interactivemedia.v3.internal.zzqd;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbs {
    public final Map<String, String> consentKeyTypes;

    public zzbs(Map<String, String> map) {
        this.consentKeyTypes = map;
    }

    public Map<String, Object> constructMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("consentKeyTypes", this.consentKeyTypes);
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return zzaho.zzf(this, obj, false, (Class) null, false, new String[0]);
    }

    public int hashCode() {
        return zzahr.zza(this, new String[0]);
    }

    public String toString() {
        zzqc zza = zzqd.zza(this);
        zza.zza("consentKeyTypes", this.consentKeyTypes);
        return zza.toString();
    }
}
