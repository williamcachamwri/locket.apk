package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzr.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbe {
    private String companionId;

    private static zzbe create(String str, String str2, String str3, zzbd zzbd) {
        return new zzr(str, str2, str3, zzbd);
    }

    public abstract String clickThroughUrl();

    public String companionId() {
        return this.companionId;
    }

    public abstract String size();

    public abstract String src();

    public final String toString() {
        String companionId2 = companionId();
        String size = size();
        String src = src();
        String clickThroughUrl = clickThroughUrl();
        String valueOf = String.valueOf(type());
        return "CompanionData [companionId=" + companionId2 + ", size=" + size + ", src=" + src + ", clickThroughUrl=" + clickThroughUrl + ", type=" + valueOf + "]";
    }

    public abstract zzbd type();

    public static zzbe create(String str, String str2, String str3, String str4, zzbd zzbd) {
        zzbe create = create(str2, str3, str4, zzbd);
        create.companionId = str;
        return create;
    }
}
