package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.CompanionAd;
import com.google.ads.interactivemedia.v3.internal.zzaho;
import com.google.ads.interactivemedia.v3.internal.zzahr;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbc implements CompanionAd {
    private String apiFramework;
    private String resourceValue;
    private zzch size = zzch.create(0, 0);

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return zzaho.zzf(this, obj, false, (Class) null, false, new String[0]);
    }

    public String getApiFramework() {
        return this.apiFramework;
    }

    public int getHeight() {
        return this.size.height().intValue();
    }

    public String getResourceValue() {
        return this.resourceValue;
    }

    public int getWidth() {
        return this.size.width().intValue();
    }

    public int hashCode() {
        return zzahr.zza(this, new String[0]);
    }

    /* access modifiers changed from: package-private */
    public void setApiFramework(String str) {
        this.apiFramework = str;
    }

    /* access modifiers changed from: package-private */
    public void setResourceValue(String str) {
        this.resourceValue = str;
    }

    /* access modifiers changed from: package-private */
    public void setSize(zzch zzch) {
        this.size = zzch;
    }

    public String toString() {
        String str = this.apiFramework;
        String str2 = this.resourceValue;
        Integer width = this.size.width();
        Integer height = this.size.height();
        return "CompanionAd [apiFramework=" + str + ", resourceUrl=" + str2 + ", width=" + width + ", height=" + height + "]";
    }
}
