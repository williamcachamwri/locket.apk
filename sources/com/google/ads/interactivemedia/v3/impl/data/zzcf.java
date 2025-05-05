package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.VersionInfo;
import com.google.ads.interactivemedia.v3.api.signals.SecureSignals;
import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzao.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzcf {
    public static zzcf createBy1stPartyData(SecureSignals secureSignals) {
        return new zzao((zzcg) null, (zzcg) null, "", secureSignals.getSecureSignal(), true);
    }

    public static zzcf createBy3rdPartyData(VersionInfo versionInfo, VersionInfo versionInfo2, String str, String str2) {
        return createBy3rdPartyData(zzcg.create(versionInfo), zzcg.create(versionInfo2), str, str2);
    }

    public abstract zzcg adapterVersion();

    public abstract Boolean isPublisherCreated();

    public abstract String name();

    public abstract zzcg sdkVersion();

    public abstract String signals();

    public static zzcf createBy3rdPartyData(zzcg zzcg, zzcg zzcg2, String str, String str2) {
        return new zzao(zzcg, zzcg2, str, str2, false);
    }
}
