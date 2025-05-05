package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzdw implements Runnable {
    zzdw() {
    }

    public final void run() {
        if (zzdz.zzc != null) {
            zzdz.zzc.post(zzdz.zzd);
            zzdz.zzc.postDelayed(zzdz.zze, 200);
        }
    }
}
