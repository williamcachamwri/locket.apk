package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzei implements Runnable {
    final Future zza;
    final zzeh zzb;

    zzei(Future future, zzeh zzeh) {
        this.zza = future;
        this.zzb = zzeh;
    }

    public final void run() {
        boolean z;
        Object obj;
        Throwable zza2 = zzey.zza((zzex) this.zza);
        if (zza2 == null) {
            try {
                Future future = this.zza;
                if (future.isDone()) {
                    z = false;
                    while (true) {
                        obj = future.get();
                        break;
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    this.zzb.zzb(obj);
                    return;
                }
                throw new IllegalStateException(zzba.zzb("Future was expected to be done: %s", future));
            } catch (InterruptedException unused) {
                z = true;
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
        zzav zza2 = zzaw.zza(this);
        zza2.zza(this.zzb);
        return zza2.toString();
    }
}
