package com.iab.omid.library.adsbynimbus.adsession;

import androidx.media3.common.MimeTypes;

public enum ErrorType {
    GENERIC("generic"),
    VIDEO(MimeTypes.BASE_TYPE_VIDEO);
    
    private final String errorType;

    private ErrorType(String str) {
        this.errorType = str;
    }

    public String toString() {
        return this.errorType;
    }
}
