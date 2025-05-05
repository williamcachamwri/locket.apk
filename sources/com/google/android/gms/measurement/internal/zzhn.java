package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzm;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final /* synthetic */ class zzhn implements Callable {
    private /* synthetic */ zzhl zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzhn(zzhl zzhl, String str) {
        this.zza = zzhl;
        this.zzb = str;
    }

    public final Object call() {
        return new zzm("internal.remoteConfig", new zzhq(this.zza, this.zzb));
    }
}
