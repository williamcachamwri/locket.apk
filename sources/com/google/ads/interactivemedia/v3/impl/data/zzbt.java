package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzaho;
import com.google.ads.interactivemedia.v3.internal.zzahr;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbt {
    public int errorCode;
    public String errorMessage;
    public String innerError;
    public String type;

    public Map<String, String> constructMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", this.type);
        hashMap.put("errorCode", String.valueOf(this.errorCode));
        hashMap.put("errorMessage", this.errorMessage);
        String str = this.innerError;
        if (str != null) {
            hashMap.put("innerError", str);
        }
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
        return String.format("Log[type=%s, errorCode=%s, errorMessage=%s, innerError=%s]", new Object[]{this.type, Integer.valueOf(this.errorCode), this.errorMessage, this.innerError});
    }
}
