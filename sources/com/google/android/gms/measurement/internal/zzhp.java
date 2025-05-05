package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzr;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final /* synthetic */ class zzhp implements Callable {
    private /* synthetic */ zzhl zza;

    public /* synthetic */ zzhp(zzhl zzhl) {
        this.zza = zzhl;
    }

    public final Object call() {
        return new zzr(this.zza.zzb);
    }
}
