package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.1.2 */
final class zzk implements Runnable {
    private final /* synthetic */ zzdo zza;
    private final /* synthetic */ zzbf zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzk(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdo zzdo, zzbf zzbf, String str) {
        this.zza = zzdo;
        this.zzb = zzbf;
        this.zzc = str;
        this.zzd = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzd.zza.zzr().zza(this.zza, this.zzb, this.zzc);
    }
}
