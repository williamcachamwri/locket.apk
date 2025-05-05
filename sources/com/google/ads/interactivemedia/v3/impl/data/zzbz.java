package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzag.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbz {
    public static final String UNKNOWN_CONTENT_TYPE = "unknown";

    private static zzbz create(String str, String str2, String str3, int i) {
        return new zzag(str, str2, str3, i);
    }

    public static zzbz forError(String str, int i) {
        return create(str, "", "unknown", i);
    }

    public static zzbz forResponse(String str, String str2) {
        return forResponse(str, str2, "unknown");
    }

    public abstract String content();

    public abstract String contentType();

    public abstract int errorCode();

    public abstract String id();

    public static zzbz forResponse(String str, String str2, String str3) {
        return create(str, str2, str3, 0);
    }
}
