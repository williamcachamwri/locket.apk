package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzuh implements Runnable {
    final Future zza;
    final zzug zzb;

    zzuh(Future future, zzug zzug) {
        this.zza = future;
        this.zzb = zzug;
    }

    public final void run() {
        Throwable zza2;
        Future future = this.zza;
        if (!(future instanceof zzvi) || (zza2 = zzvj.zza((zzvi) future)) == null) {
            try {
                this.zzb.zzb(zzuk.zzd(this.zza));
            } catch (ExecutionException e) {
                this.zzb.zza(e.getCause());
            } catch (Throwable th) {
                this.zzb.zza(th);
            }
        } else {
            this.zzb.zza(zza2);
        }
    }

    public final String toString() {
        zzqc zza2 = zzqd.zza(this);
        zza2.zzb(this.zzb);
        return zza2.toString();
    }
}
