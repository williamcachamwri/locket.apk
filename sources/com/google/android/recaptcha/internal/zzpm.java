package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzpm extends RuntimeException {
    public zzpm(zzok zzok) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zznp zza() {
        return new zznp(getMessage());
    }
}
