package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzir implements Callable<List<zzae>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzic zzd;

    public final /* synthetic */ Object call() throws Exception {
        this.zzd.zza.zzr();
        return this.zzd.zza.zzf().zza(this.zza, this.zzb, this.zzc);
    }

    zzir(zzic zzic, String str, String str2, String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzic;
    }
}
