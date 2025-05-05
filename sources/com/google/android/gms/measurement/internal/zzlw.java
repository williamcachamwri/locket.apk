package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzlw implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzo zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzdo zze;
    private final /* synthetic */ zzls zzf;

    zzlw(zzls zzls, String str, String str2, zzo zzo, boolean z, zzdo zzdo) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzo;
        this.zzd = z;
        this.zze = zzdo;
        this.zzf = zzls;
    }

    public final void run() {
        Bundle bundle = new Bundle();
        try {
            zzgb zza2 = this.zzf.zzb;
            if (zza2 == null) {
                this.zzf.zzj().zzg().zza("Failed to get user properties; not connected to service", this.zza, this.zzb);
                return;
            }
            Preconditions.checkNotNull(this.zzc);
            bundle = zzos.zza(zza2.zza(this.zza, this.zzb, this.zzd, this.zzc));
            this.zzf.zzar();
            this.zzf.zzq().zza(this.zze, bundle);
        } catch (RemoteException e) {
            this.zzf.zzj().zzg().zza("Failed to get user properties; remote exception", this.zza, e);
        } finally {
            this.zzf.zzq().zza(this.zze, bundle);
        }
    }
}
