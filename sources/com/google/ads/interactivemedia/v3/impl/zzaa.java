package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.StreamManager;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzaa implements AdsManagerLoadedEvent {
    private final AdsManager zza;
    private final StreamManager zzb;
    private final Object zzc;

    zzaa(AdsManager adsManager, Object obj) {
        this.zza = adsManager;
        this.zzb = null;
        this.zzc = obj;
    }

    zzaa(StreamManager streamManager, Object obj) {
        this.zza = null;
        this.zzb = streamManager;
        this.zzc = obj;
    }

    public final AdsManager getAdsManager() {
        return this.zza;
    }

    public final StreamManager getStreamManager() {
        return this.zzb;
    }

    public final Object getUserRequestContext() {
        return this.zzc;
    }
}
