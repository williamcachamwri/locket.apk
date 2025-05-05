package com.iab.omid.library.adsbynimbus.adsession;

import androidx.media3.common.MimeTypes;

public enum CreativeType {
    DEFINED_BY_JAVASCRIPT("definedByJavaScript"),
    HTML_DISPLAY("htmlDisplay"),
    NATIVE_DISPLAY("nativeDisplay"),
    VIDEO(MimeTypes.BASE_TYPE_VIDEO),
    AUDIO(MimeTypes.BASE_TYPE_AUDIO);
    
    private final String creativeType;

    private CreativeType(String str) {
        this.creativeType = str;
    }

    public String toString() {
        return this.creativeType;
    }
}
