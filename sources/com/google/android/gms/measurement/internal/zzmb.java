package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzmb implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzls zzb;

    zzmb(zzls zzls, zzo zzo) {
        this.zza = zzo;
        this.zzb = zzls;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zzd(this.zza);
            this.zzb.zzh().zzac();
            this.zzb.zza(zza2, (AbstractSafeParcelable) null, this.zza);
            this.zzb.zzar();
        } catch (RemoteException e) {
            this.zzb.zzj().zzg().zza("Failed to send app launch to the service", e);
        }
    }
}
