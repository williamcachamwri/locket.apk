package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzmj implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzbf zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zzls zzf;

    zzmj(zzls zzls, boolean z, zzo zzo, boolean z2, zzbf zzbf, String str) {
        this.zza = z;
        this.zzb = zzo;
        this.zzc = z2;
        this.zzd = zzbf;
        this.zze = str;
        this.zzf = zzls;
    }

    public final void run() {
        long j;
        long j2;
        long j3;
        zzgb zza2 = this.zzf.zzb;
        if (zza2 == null) {
            this.zzf.zzj().zzg().zza("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zza) {
            Preconditions.checkNotNull(this.zzb);
            this.zzf.zza(zza2, (AbstractSafeParcelable) this.zzc ? null : this.zzd, this.zzb);
        } else {
            boolean zza3 = this.zzf.zze().zza(zzbh.zzce);
            try {
                if (TextUtils.isEmpty(this.zze)) {
                    Preconditions.checkNotNull(this.zzb);
                    if (zza3) {
                        j3 = this.zzf.zzu.zzb().currentTimeMillis();
                        try {
                            j2 = this.zzf.zzu.zzb().elapsedRealtime();
                        } catch (RemoteException e) {
                            e = e;
                            j2 = 0;
                            j = j3;
                            this.zzf.zzj().zzg().zza("Failed to send event to the service", e);
                            zzgm.zza(this.zzf.zzu).zza(36301, 13, j, this.zzf.zzu.zzb().currentTimeMillis(), (int) (this.zzf.zzu.zzb().elapsedRealtime() - j2));
                            this.zzf.zzar();
                        }
                    } else {
                        j3 = 0;
                        j2 = 0;
                    }
                    try {
                        zza2.zza(this.zzd, this.zzb);
                        if (zza3) {
                            this.zzf.zzj().zzp().zza("Logging telemetry for logEvent");
                            zzgm.zza(this.zzf.zzu).zza(36301, 0, j3, this.zzf.zzu.zzb().currentTimeMillis(), (int) (this.zzf.zzu.zzb().elapsedRealtime() - j2));
                        }
                    } catch (RemoteException e2) {
                        e = e2;
                        j = j3;
                        this.zzf.zzj().zzg().zza("Failed to send event to the service", e);
                        if (zza3 && j != 0) {
                            zzgm.zza(this.zzf.zzu).zza(36301, 13, j, this.zzf.zzu.zzb().currentTimeMillis(), (int) (this.zzf.zzu.zzb().elapsedRealtime() - j2));
                        }
                        this.zzf.zzar();
                    }
                } else {
                    zza2.zza(this.zzd, this.zze, this.zzf.zzj().zzx());
                }
            } catch (RemoteException e3) {
                e = e3;
                j2 = 0;
                j = 0;
            }
        }
        this.zzf.zzar();
    }
}
