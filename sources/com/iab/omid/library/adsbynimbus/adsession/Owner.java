package com.iab.omid.library.adsbynimbus.adsession;

import io.sentry.protocol.SentryStackFrame;

public enum Owner {
    NATIVE(SentryStackFrame.JsonKeys.NATIVE),
    JAVASCRIPT("javascript"),
    NONE("none");
    
    private final String owner;

    private Owner(String str) {
        this.owner = str;
    }

    public String toString() {
        return this.owner;
    }
}
