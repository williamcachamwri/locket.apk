package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzaw.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzcn {
    public static zzcn create(Integer num, boolean z) {
        return new zzaw(num, z);
    }

    public abstract Integer downloadBandwidthKbps();

    public abstract boolean rendersUiNatively();
}
