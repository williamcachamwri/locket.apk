package com.google.ads.interactivemedia.omid.library.adsession;

import io.sentry.protocol.SentryStackFrame;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public enum zzi {
    NATIVE(SentryStackFrame.JsonKeys.NATIVE),
    JAVASCRIPT("javascript"),
    NONE("none");
    
    private final String zze;

    private zzi(String str) {
        this.zze = str;
    }

    public final String toString() {
        return this.zze;
    }
}
