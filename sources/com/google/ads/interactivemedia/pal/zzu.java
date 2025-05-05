package com.google.ads.interactivemedia.pal;

import io.sentry.SentryEnvelopeItemHeader;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
enum zzu {
    ERROR_CODE("errcode"),
    NONCE_LENGTH(SentryEnvelopeItemHeader.JsonKeys.LENGTH),
    NONCE_LOADED_TIME("nonload"),
    NONCE_LOADER_INIT_TIME("loaderinit"),
    NONCE_REQUESTED_TIME("nonreq"),
    SERVICE_END_TIME("srvcend"),
    SERVICE_START_TIME("srvcstrt");
    
    private final String zzi;

    private zzu(String str) {
        this.zzi = str;
    }

    /* access modifiers changed from: package-private */
    public final String zza() {
        return this.zzi;
    }
}
