package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdh;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
abstract class zzav {
    private static volatile Handler zza;
    private final zzjc zzb;
    private final Runnable zzc;
    /* access modifiers changed from: private */
    public volatile long zzd;

    private final Handler zzd() {
        Handler handler;
        if (zza != null) {
            return zza;
        }
        synchronized (zzav.class) {
            if (zza == null) {
                zza = new zzdh(this.zzb.zza().getMainLooper());
            }
            handler = zza;
        }
        return handler;
    }

    public abstract void zzb();

    zzav(zzjc zzjc) {
        Preconditions.checkNotNull(zzjc);
        this.zzb = zzjc;
        this.zzc = new zzay(this, zzjc);
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzd = 0;
        zzd().removeCallbacks(this.zzc);
    }

    public final void zza(long j) {
        zza();
        if (j >= 0) {
            this.zzd = this.zzb.zzb().currentTimeMillis();
            if (!zzd().postDelayed(this.zzc, j)) {
                this.zzb.zzj().zzg().zza("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean zzc() {
        return this.zzd != 0;
    }
}
