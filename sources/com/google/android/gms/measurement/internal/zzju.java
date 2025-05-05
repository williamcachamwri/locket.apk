package com.google.android.gms.measurement.internal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzju implements Runnable {
    private /* synthetic */ zzjq zza;
    private /* synthetic */ List zzb;

    public /* synthetic */ zzju(zzjq zzjq, List list) {
        this.zza = zzjq;
        this.zzb = list;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
