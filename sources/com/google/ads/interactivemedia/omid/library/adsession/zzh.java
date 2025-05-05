package com.google.ads.interactivemedia.omid.library.adsession;

import com.google.firebase.ktx.BuildConfig;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public enum zzh {
    DEFINED_BY_JAVASCRIPT("definedByJavaScript"),
    UNSPECIFIED(BuildConfig.VERSION_NAME),
    LOADED("loaded"),
    BEGIN_TO_RENDER("beginToRender"),
    ONE_PIXEL("onePixel"),
    VIEWABLE("viewable"),
    AUDIBLE("audible"),
    OTHER("other");
    
    private final String zzj;

    private zzh(String str) {
        this.zzj = str;
    }

    public final String toString() {
        return this.zzj;
    }
}
