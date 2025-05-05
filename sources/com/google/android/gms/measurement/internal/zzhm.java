package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzx;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final /* synthetic */ class zzhm implements Callable {
    private /* synthetic */ zzhl zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzhm(zzhl zzhl, String str) {
        this.zza = zzhl;
        this.zzb = str;
    }

    public final Object call() {
        return new zzx("internal.appMetadata", new zzhk(this.zza, this.zzb));
    }
}
