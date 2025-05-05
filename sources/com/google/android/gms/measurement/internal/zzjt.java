package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzjt implements Runnable {
    private /* synthetic */ zzjq zza;
    private /* synthetic */ Bundle zzb;

    public /* synthetic */ zzjt(zzjq zzjq, Bundle bundle) {
        this.zza = zzjq;
        this.zzb = bundle;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
