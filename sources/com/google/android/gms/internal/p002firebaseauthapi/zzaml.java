package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaml  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaml extends RuntimeException {
    public final zzakf zza() {
        return new zzakf(getMessage());
    }

    public zzaml(zzalc zzalc) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }
}
