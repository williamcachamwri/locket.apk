package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzagg extends RuntimeException {
    public zzagg(zzafb zzafb) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzaeg zza() {
        return new zzaeg(getMessage());
    }
}
