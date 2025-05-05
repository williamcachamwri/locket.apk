package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final /* synthetic */ class zzie implements Runnable {
    private /* synthetic */ zzic zza;
    private /* synthetic */ Bundle zzb;
    private /* synthetic */ String zzc;

    public /* synthetic */ zzie(zzic zzic, Bundle bundle, String str) {
        this.zza = zzic;
        this.zzb = bundle;
        this.zzc = str;
    }

    public final void run() {
        this.zza.zzb(this.zzb, this.zzc);
    }
}
