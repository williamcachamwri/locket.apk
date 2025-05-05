package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzis implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzic zzb;

    zzis(zzic zzic, zzo zzo) {
        this.zza = zzo;
        this.zzb = zzic;
    }

    public final void run() {
        this.zzb.zza.zzr();
        zznv zza2 = this.zzb.zza;
        zzo zzo = this.zza;
        zza2.zzl().zzt();
        zza2.zzs();
        Preconditions.checkNotEmpty(zzo.zza);
        zza2.zzg(zzo);
        zza2.zzf(zzo);
    }
}
