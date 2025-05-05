package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final /* synthetic */ class zzmx implements Runnable {
    private /* synthetic */ zzmy zza;
    private /* synthetic */ zzgo zzb;
    private /* synthetic */ JobParameters zzc;

    public /* synthetic */ zzmx(zzmy zzmy, zzgo zzgo, JobParameters jobParameters) {
        this.zza = zzmy;
        this.zzb = zzgo;
        this.zzc = jobParameters;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
