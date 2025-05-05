package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzfd;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzt implements Runnable {
    public final /* synthetic */ zzahj zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzt(zzahj zzahj, long j) {
        this.zza = zzahj;
        this.zzb = j;
    }

    public final void run() {
        this.zza.zzj(zzfd.zza(this.zzb, System.currentTimeMillis()));
    }
}
