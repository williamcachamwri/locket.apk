package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzr implements Runnable {
    private /* synthetic */ zzhy zza;

    public /* synthetic */ zzr(zzhy zzhy) {
        this.zza = zzhy;
    }

    public final void run() {
        zzhy zzhy = this.zza;
        if (!zzhy.zzt().zzw()) {
            zzhy.zzj().zzu().zza("registerTrigger called but app not eligible");
            return;
        }
        zzjq zzp = zzhy.zzp();
        Objects.requireNonNull(zzp);
        new Thread(new zzs(zzp)).start();
    }
}
