package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdw;
import com.google.android.gms.measurement.internal.zznc;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzmy<T extends Context & zznc> {
    private final T zza;

    public final int zza(Intent intent, int i, int i2) {
        zzgo zzj = zzhy.zza(this.zza, (zzdw) null, (Long) null).zzj();
        if (intent == null) {
            zzj.zzu().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzj.zzp().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zza((Runnable) new zzna(this, i2, zzj, intent));
        }
        return 2;
    }

    public final IBinder zza(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzic(zznv.zza((Context) this.zza));
        }
        zzc().zzu().zza("onBind received unknown action", action);
        return null;
    }

    private final zzgo zzc() {
        return zzhy.zza(this.zza, (zzdw) null, (Long) null).zzj();
    }

    public zzmy(T t) {
        Preconditions.checkNotNull(t);
        this.zza = t;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzgo zzgo, Intent intent) {
        if (((zznc) this.zza).zza(i)) {
            zzgo.zzp().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzc().zzp().zza("Completed wakeful intent.");
            ((zznc) this.zza).zza(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzgo zzgo, JobParameters jobParameters) {
        zzgo.zzp().zza("AppMeasurementJobService processed last upload request.");
        ((zznc) this.zza).zza(jobParameters, false);
    }

    public final void zza() {
        zzhy.zza(this.zza, (zzdw) null, (Long) null).zzj().zzp().zza("Local AppMeasurementService is starting up");
    }

    public final void zzb() {
        zzhy.zza(this.zza, (zzdw) null, (Long) null).zzj().zzp().zza("Local AppMeasurementService is shutting down");
    }

    public final void zzb(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onRebind called with null intent");
            return;
        }
        zzc().zzp().zza("onRebind called. action", intent.getAction());
    }

    private final void zza(Runnable runnable) {
        zznv zza2 = zznv.zza((Context) this.zza);
        zza2.zzl().zzb((Runnable) new zzmz(this, zza2, runnable));
    }

    public final boolean zza(JobParameters jobParameters) {
        zzgo zzj = zzhy.zza(this.zza, (zzdw) null, (Long) null).zzj();
        String string = jobParameters.getExtras().getString("action");
        zzj.zzp().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza((Runnable) new zzmx(this, zzj, jobParameters));
        return true;
    }

    public final boolean zzc(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onUnbind called with null intent");
            return true;
        }
        zzc().zzp().zza("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
