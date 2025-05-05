package com.google.ads.interactivemedia.v3.impl;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzw {
    public static zzw create(int i, String str) {
        return new AutoValue_AdsLoaderImpl_MarketAppInfo(i, str);
    }

    public abstract int appVersion();

    public abstract String packageName();
}
