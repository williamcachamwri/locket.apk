package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzac.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbn {
    public static zzbn create(String str, String str2, boolean z, String str3, int i, String str4) {
        return new zzac(str, str2, z, str3, i, str4);
    }

    public abstract String adsIdentityToken();

    public abstract String appSetId();

    public abstract int appSetIdScope();

    public abstract String deviceId();

    public abstract String idType();

    public abstract boolean isLimitedAdTracking();
}
