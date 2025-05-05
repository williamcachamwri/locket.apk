package com.google.android.play.core.appupdate.internal;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzr extends zzn {
    final /* synthetic */ zzx zza;

    zzr(zzx zzx) {
        this.zza = zzx;
    }

    public final void zza() {
        synchronized (this.zza.zzg) {
            if (this.zza.zzl.get() > 0) {
                if (this.zza.zzl.decrementAndGet() > 0) {
                    this.zza.zzc.zzd("Leaving the connection open for other ongoing calls.", new Object[0]);
                    return;
                }
            }
            zzx zzx = this.zza;
            if (zzx.zzn != null) {
                zzx.zzc.zzd("Unbind from service.", new Object[0]);
                zzx zzx2 = this.zza;
                zzx2.zzb.unbindService(zzx2.zzm);
                this.zza.zzh = false;
                this.zza.zzn = null;
                this.zza.zzm = null;
            }
            this.zza.zzw();
        }
    }
}
