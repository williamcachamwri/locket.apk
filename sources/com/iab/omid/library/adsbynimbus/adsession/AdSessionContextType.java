package com.iab.omid.library.adsbynimbus.adsession;

import io.sentry.protocol.SentryStackFrame;

public enum AdSessionContextType {
    HTML("html"),
    NATIVE(SentryStackFrame.JsonKeys.NATIVE),
    JAVASCRIPT("javascript");
    
    private final String typeString;

    private AdSessionContextType(String str) {
        this.typeString = str;
    }

    public String toString() {
        return this.typeString;
    }
}
