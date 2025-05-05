package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzaf.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzby {
    public static zzby create(zzbx zzbx, String str, String str2, String str3, String str4, int i, int i2) {
        return new zzaf(zzbx, str, str2, str4, str3, i, i2);
    }

    public abstract int connectionTimeoutMs();

    public abstract String content();

    public abstract String id();

    public abstract int readTimeoutMs();

    public abstract zzbx requestType();

    public abstract String url();

    public abstract String userAgent();
}
