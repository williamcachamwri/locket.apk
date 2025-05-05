package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzad.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbr {
    public static zzbr create(long j, AdErrorEvent adErrorEvent) {
        return create(j, (zzbp) null, (zzbq) null, adErrorEvent, (zzbw) null);
    }

    public static zzbr create(long j, zzbp zzbp, zzbq zzbq) {
        return create(j, zzbp, zzbq, (AdErrorEvent) null, (zzbw) null);
    }

    public static zzbr create(long j, zzbp zzbp, zzbq zzbq, long j2) {
        return new zzad(j, zzbp, zzbq, (AdErrorEvent) null, (zzbw) null, Long.valueOf(j2), (String) null);
    }

    private static zzbr create(long j, zzbp zzbp, zzbq zzbq, AdErrorEvent adErrorEvent, zzbw zzbw) {
        return new zzad(j, zzbp, zzbq, adErrorEvent, zzbw, (Long) null, (String) null);
    }

    public static zzbr create(long j, zzbp zzbp, zzbq zzbq, String str) {
        return new zzad(j, zzbp, zzbq, (AdErrorEvent) null, (zzbw) null, (Long) null, str);
    }

    public static zzbr create(long j, zzbp zzbp, zzbq zzbq, Throwable th) {
        return create(j, zzbp, zzbq, (AdErrorEvent) null, zzbw.create(th));
    }

    public abstract AdErrorEvent adErrorEvent();

    public abstract zzbp component();

    public abstract Long latency();

    public abstract String latencyMeasurementProtoBase64String();

    public abstract zzbw loggableException();

    public abstract zzbq method();

    public abstract long timestamp();

    public static zzbr create(long j, zzbp zzbp, zzbq zzbq, long j2, String str) {
        return new zzad(j, zzbp, zzbq, (AdErrorEvent) null, (zzbw) null, Long.valueOf(j2), str);
    }
}
