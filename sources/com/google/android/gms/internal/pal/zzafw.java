package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzafw extends IllegalArgumentException {
    zzafw(int i, int i2) {
        super("Unpaired surrogate at index " + i + " of " + i2);
    }
}
