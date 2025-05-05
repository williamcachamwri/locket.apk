package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.VersionInfo;
import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzap.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzcg {
    public static zzcg create(int i, int i2, int i3) {
        return new zzap(i, i2, i3);
    }

    public static zzcg create(VersionInfo versionInfo) {
        return create(versionInfo.getMajorVersion(), versionInfo.getMinorVersion(), versionInfo.getMicroVersion());
    }

    public abstract int major();

    public abstract int micro();

    public abstract int minor();
}
