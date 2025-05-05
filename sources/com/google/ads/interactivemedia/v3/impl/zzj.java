package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzfd;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzj implements Runnable {
    public final /* synthetic */ zzahj zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzj(zzahj zzahj, long j) {
        this.zza = zzahj;
        this.zzb = j;
    }

    public final void run() {
        this.zza.zzl(zzfd.zza(this.zzb, System.currentTimeMillis()));
    }
}
