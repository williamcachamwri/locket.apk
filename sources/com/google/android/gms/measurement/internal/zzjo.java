package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdw;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzjo {
    final Context zza;
    String zzb;
    String zzc;
    String zzd;
    Boolean zze;
    long zzf;
    zzdw zzg;
    boolean zzh = true;
    Long zzi;
    String zzj;

    public zzjo(Context context, zzdw zzdw, Long l) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.zza = applicationContext;
        this.zzi = l;
        if (zzdw != null) {
            this.zzg = zzdw;
            this.zzb = zzdw.zzf;
            this.zzc = zzdw.zze;
            this.zzd = zzdw.zzd;
            this.zzh = zzdw.zzc;
            this.zzf = zzdw.zzb;
            this.zzj = zzdw.zzh;
            if (zzdw.zzg != null) {
                this.zze = Boolean.valueOf(zzdw.zzg.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
