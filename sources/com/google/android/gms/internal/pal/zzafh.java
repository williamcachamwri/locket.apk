package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzafh extends RuntimeException {
    public zzafh(zzaef zzaef) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzadi zza() {
        return new zzadi(getMessage());
    }
}
