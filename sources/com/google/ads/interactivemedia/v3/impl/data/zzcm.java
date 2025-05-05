package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.UniversalAdId;
import com.google.ads.interactivemedia.v3.internal.zzaho;
import com.google.ads.interactivemedia.v3.internal.zzahr;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcm implements UniversalAdId {
    private String adIdRegistry = "unknown";
    private String adIdValue = "unknown";

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return zzaho.zzf(this, obj, false, (Class) null, false, new String[0]);
    }

    public String getAdIdRegistry() {
        return this.adIdRegistry;
    }

    public String getAdIdValue() {
        return this.adIdValue;
    }

    public int hashCode() {
        return zzahr.zza(this, new String[0]);
    }

    public void setAdIdRegistry(String str) {
        this.adIdRegistry = str;
    }

    public void setAdIdValue(String str) {
        this.adIdValue = str;
    }

    public String toString() {
        String str = this.adIdValue;
        String str2 = this.adIdRegistry;
        return "UniversalAdId [adIdValue=" + str + ", adIdRegistry=" + str2 + "]";
    }
}
