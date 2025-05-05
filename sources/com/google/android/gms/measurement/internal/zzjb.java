package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzjb implements Callable<List<zzop>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzic zzb;

    public final /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzr();
        return this.zzb.zza.zzf().zzl(this.zza);
    }

    zzjb(zzic zzic, String str) {
        this.zza = str;
        this.zzb = zzic;
    }
}
