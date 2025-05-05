package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzmd implements Runnable {
    private final /* synthetic */ zzlk zza;
    private final /* synthetic */ zzls zzb;

    zzmd(zzls zzls, zzlk zzlk) {
        this.zza = zzlk;
        this.zzb = zzls;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzlk zzlk = this.zza;
            if (zzlk == null) {
                zza2.zza(0, (String) null, (String) null, this.zzb.zza().getPackageName());
            } else {
                zza2.zza(zzlk.zzc, this.zza.zza, this.zza.zzb, this.zzb.zza().getPackageName());
            }
            this.zzb.zzar();
        } catch (RemoteException e) {
            this.zzb.zzj().zzg().zza("Failed to send current screen to the service", e);
        }
    }
}
