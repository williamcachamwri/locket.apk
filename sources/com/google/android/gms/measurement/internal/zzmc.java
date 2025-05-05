package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzmc implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzdo zzb;
    private final /* synthetic */ zzls zzc;

    zzmc(zzls zzls, zzo zzo, zzdo zzdo) {
        this.zza = zzo;
        this.zzb = zzdo;
        this.zzc = zzls;
    }

    public final void run() {
        try {
            if (!this.zzc.zzk().zzo().zzh()) {
                this.zzc.zzj().zzv().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zzm().zzc((String) null);
                this.zzc.zzk().zze.zza((String) null);
                return;
            }
            zzgb zza2 = this.zzc.zzb;
            if (zza2 == null) {
                this.zzc.zzj().zzg().zza("Failed to get app instance id");
                this.zzc.zzq().zza(this.zzb, (String) null);
                return;
            }
            Preconditions.checkNotNull(this.zza);
            String zzb2 = zza2.zzb(this.zza);
            if (zzb2 != null) {
                this.zzc.zzm().zzc(zzb2);
                this.zzc.zzk().zze.zza(zzb2);
            }
            this.zzc.zzar();
            this.zzc.zzq().zza(this.zzb, zzb2);
        } catch (RemoteException e) {
            this.zzc.zzj().zzg().zza("Failed to get app instance id", e);
        } finally {
            this.zzc.zzq().zza(this.zzb, (String) null);
        }
    }
}
