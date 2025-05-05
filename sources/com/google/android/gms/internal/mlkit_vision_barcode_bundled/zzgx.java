package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzgx extends RuntimeException {
    public zzgx(zzfo zzfo) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzeo zza() {
        return new zzeo(getMessage());
    }
}
