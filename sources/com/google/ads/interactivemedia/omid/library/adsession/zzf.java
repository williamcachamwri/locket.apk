package com.google.ads.interactivemedia.omid.library.adsession;

import androidx.media3.common.MimeTypes;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public enum zzf {
    DEFINED_BY_JAVASCRIPT("definedByJavaScript"),
    HTML_DISPLAY("htmlDisplay"),
    NATIVE_DISPLAY("nativeDisplay"),
    VIDEO(MimeTypes.BASE_TYPE_VIDEO),
    AUDIO(MimeTypes.BASE_TYPE_AUDIO);
    
    private final String zzg;

    private zzf(String str) {
        this.zzg = str;
    }

    public final String toString() {
        return this.zzg;
    }
}
