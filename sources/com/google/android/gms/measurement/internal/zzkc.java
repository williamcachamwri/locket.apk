package com.google.android.gms.measurement.internal;

import android.util.SparseArray;
import com.google.common.util.concurrent.FutureCallback;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzkc implements FutureCallback<Object> {
    private final /* synthetic */ zzno zza;
    private final /* synthetic */ zzjq zzb;

    zzkc(zzjq zzjq, zzno zzno) {
        this.zza = zzno;
        this.zzb = zzjq;
    }

    public final void onFailure(Throwable th) {
        this.zzb.zzt();
        this.zzb.zzh = false;
        if (!this.zzb.zze().zza(zzbh.zzcn)) {
            this.zzb.zzas();
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed with throwable", th);
            return;
        }
        int zza2 = (this.zzb.zze().zza(zzbh.zzcl) ? zzjq.zza(this.zzb, th) : 2) - 1;
        if (zza2 == 0) {
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed with retriable error. Will try later. App ID, throwable", zzgo.zza(this.zzb.zzg().zzad()), zzgo.zza(th.toString()));
            this.zzb.zzi = 1;
            this.zzb.zzal().add(this.zza);
        } else if (zza2 == 1) {
            this.zzb.zzal().add(this.zza);
            if (this.zzb.zzi > 32) {
                this.zzb.zzi = 1;
                this.zzb.zzj().zzu().zza("registerTriggerAsync failed. May try later. App ID, throwable", zzgo.zza(this.zzb.zzg().zzad()), zzgo.zza(th.toString()));
                return;
            }
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed. App ID, delay in seconds, throwable", zzgo.zza(this.zzb.zzg().zzad()), zzgo.zza(String.valueOf(this.zzb.zzi)), zzgo.zza(th.toString()));
            zzjq zzjq = this.zzb;
            zzjq.zzb(zzjq, zzjq.zzi);
            zzjq zzjq2 = this.zzb;
            zzjq2.zzi = zzjq2.zzi << 1;
        } else if (zza2 == 2) {
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed. Dropping URI. App ID, Throwable", zzgo.zza(this.zzb.zzg().zzad()), th);
            zza();
            this.zzb.zzi = 1;
            this.zzb.zzas();
        }
    }

    public final void onSuccess(Object obj) {
        this.zzb.zzt();
        if (this.zzb.zze().zza(zzbh.zzcn)) {
            zza();
            this.zzb.zzh = false;
            this.zzb.zzi = 1;
            this.zzb.zzj().zzc().zza("Successfully registered trigger URI", this.zza.zza);
            this.zzb.zzas();
            return;
        }
        this.zzb.zzh = false;
        this.zzb.zzas();
        this.zzb.zzj().zzc().zza("registerTriggerAsync ran. uri", this.zza.zza);
    }

    private final void zza() {
        SparseArray<Long> zzm = this.zzb.zzk().zzm();
        zzm.put(this.zza.zzc, Long.valueOf(this.zza.zzb));
        this.zzb.zzk().zza(zzm);
    }
}
