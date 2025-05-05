package com.google.mlkit.vision.common;

import android.media.Image;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzb {
    private final Image zza;

    zzb(Image image) {
        this.zza = image;
    }

    /* access modifiers changed from: package-private */
    public final Image zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final Image.Plane[] zzb() {
        return this.zza.getPlanes();
    }
}
