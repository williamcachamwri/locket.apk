package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzms implements Runnable {
    private final /* synthetic */ ComponentName zza;
    private final /* synthetic */ zzmq zzb;

    zzms(zzmq zzmq, ComponentName componentName) {
        this.zza = componentName;
        this.zzb = zzmq;
    }

    public final void run() {
        zzls.zza(this.zzb.zza, this.zza);
    }
}
