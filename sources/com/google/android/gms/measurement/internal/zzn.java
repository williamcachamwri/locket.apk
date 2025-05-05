package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
final class zzn implements Runnable {
    private final /* synthetic */ zzdo zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzn(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdo zzdo) {
        this.zza = zzdo;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzt().zza(this.zza, this.zzb.zza.zzab());
    }
}
