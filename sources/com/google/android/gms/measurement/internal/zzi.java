package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
final class zzi implements Runnable {
    private final /* synthetic */ zzdo zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzi(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdo zzdo) {
        this.zza = zzdo;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzr().zza(this.zza);
    }
}
