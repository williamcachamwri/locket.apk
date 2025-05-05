package com.google.ads.interactivemedia.v3.api;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public interface AdErrorEvent {

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public interface AdErrorListener {
        void onAdError(AdErrorEvent adErrorEvent);
    }

    AdError getError();

    Object getUserRequestContext();
}
