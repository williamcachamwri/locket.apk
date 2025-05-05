package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final /* synthetic */ class zzig implements Runnable {
    private /* synthetic */ zzic zza;
    private /* synthetic */ Bundle zzb;
    private /* synthetic */ String zzc;

    public /* synthetic */ zzig(zzic zzic, Bundle bundle, String str) {
        this.zza = zzic;
        this.zzb = bundle;
        this.zzc = str;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
