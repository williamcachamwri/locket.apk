package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzog implements Callable<String> {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zznv zzb;

    public final /* synthetic */ Object call() throws Exception {
        if (!this.zzb.zzb((String) Preconditions.checkNotNull(this.zza.zza)).zzh() || !zzje.zzb(this.zza.zzt).zzh()) {
            this.zzb.zzj().zzp().zza("Analytics storage consent denied. Returning null app instance id");
            return null;
        }
        zzg zza2 = this.zzb.zza(this.zza);
        if (zza2 != null) {
            return zza2.zzad();
        }
        this.zzb.zzj().zzu().zza("App info was null when attempting to get app instance id");
        return null;
    }

    zzog(zznv zznv, zzo zzo) {
        this.zza = zzo;
        this.zzb = zznv;
    }
}
