package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzk.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzb {
    public static zza builder() {
        return new zzi();
    }

    public abstract String appState();

    public abstract String eventId();

    public abstract long nativeTime();

    public abstract zzbb nativeViewBounds();

    public abstract boolean nativeViewHidden();

    public abstract zzbb nativeViewVisibleBounds();

    public abstract double nativeVolume();

    public abstract String queryId();
}
