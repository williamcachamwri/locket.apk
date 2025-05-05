package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzec {
    private final BlockingQueue zza;
    private final ThreadPoolExecutor zzb;
    private final ArrayDeque zzc = new ArrayDeque();
    private zzeb zzd = null;

    public zzec() {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        this.zza = linkedBlockingQueue;
        this.zzb = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, linkedBlockingQueue);
    }

    private final void zzc() {
        zzeb zzeb = (zzeb) this.zzc.poll();
        this.zzd = zzeb;
        if (zzeb != null) {
            zzeb.executeOnExecutor(this.zzb, new Object[0]);
        }
    }

    public final void zza(zzeb zzeb) {
        this.zzd = null;
        zzc();
    }

    public final void zzb(zzeb zzeb) {
        zzeb.zzb(this);
        this.zzc.add(zzeb);
        if (this.zzd == null) {
            zzc();
        }
    }
}
