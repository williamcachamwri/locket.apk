package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public final class zzgy extends RuntimeException {
    public zzgy(zzfx zzfx) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzfb zza() {
        return new zzfb(getMessage());
    }
}
