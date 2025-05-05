package com.google.ads.interactivemedia.v3.api;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public enum FriendlyObstructionPurpose {
    VIDEO_CONTROLS,
    CLOSE_AD,
    NOT_VISIBLE,
    OTHER;

    public com.google.ads.interactivemedia.omid.library.adsession.FriendlyObstructionPurpose getOmidPurpose() {
        return (com.google.ads.interactivemedia.omid.library.adsession.FriendlyObstructionPurpose) com.google.ads.interactivemedia.omid.library.adsession.FriendlyObstructionPurpose.valueOf(com.google.ads.interactivemedia.omid.library.adsession.FriendlyObstructionPurpose.class, name());
    }
}
