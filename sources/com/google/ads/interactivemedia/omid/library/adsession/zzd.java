package com.google.ads.interactivemedia.omid.library.adsession;

import io.sentry.protocol.SentryStackFrame;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public enum zzd {
    HTML("html"),
    NATIVE(SentryStackFrame.JsonKeys.NATIVE),
    JAVASCRIPT("javascript");
    
    private final String zze;

    private zzd(String str) {
        this.zze = str;
    }

    public final String toString() {
        return this.zze;
    }
}
