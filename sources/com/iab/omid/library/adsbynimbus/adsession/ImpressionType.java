package com.iab.omid.library.adsbynimbus.adsession;

import com.google.firebase.ktx.BuildConfig;

public enum ImpressionType {
    DEFINED_BY_JAVASCRIPT("definedByJavaScript"),
    UNSPECIFIED(BuildConfig.VERSION_NAME),
    LOADED("loaded"),
    BEGIN_TO_RENDER("beginToRender"),
    ONE_PIXEL("onePixel"),
    VIEWABLE("viewable"),
    AUDIBLE("audible"),
    OTHER("other");
    
    private final String impressionType;

    private ImpressionType(String str) {
        this.impressionType = str;
    }

    public String toString() {
        return this.impressionType;
    }
}
