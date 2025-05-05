package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzmi implements Runnable {
    private final /* synthetic */ zzbf zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzdo zzc;
    private final /* synthetic */ zzls zzd;

    zzmi(zzls zzls, zzbf zzbf, String str, zzdo zzdo) {
        this.zza = zzbf;
        this.zzb = str;
        this.zzc = zzdo;
        this.zzd = zzls;
    }

    public final void run() {
        byte[] bArr = null;
        try {
            zzgb zza2 = this.zzd.zzb;
            if (zza2 == null) {
                this.zzd.zzj().zzg().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            bArr = zza2.zza(this.zza, this.zzb);
            this.zzd.zzar();
            this.zzd.zzq().zza(this.zzc, bArr);
        } catch (RemoteException e) {
            this.zzd.zzj().zzg().zza("Failed to send event to the service to bundle", e);
        } finally {
            this.zzd.zzq().zza(this.zzc, bArr);
        }
    }
}
