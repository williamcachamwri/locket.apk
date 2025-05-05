package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzlx implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzon zzc;
    private final /* synthetic */ zzls zzd;

    zzlx(zzls zzls, zzo zzo, boolean z, zzon zzon) {
        this.zza = zzo;
        this.zzb = z;
        this.zzc = zzon;
        this.zzd = zzls;
    }

    public final void run() {
        zzgb zza2 = this.zzd.zzb;
        if (zza2 == null) {
            this.zzd.zzj().zzg().zza("Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zzd.zza(zza2, (AbstractSafeParcelable) this.zzb ? null : this.zzc, this.zza);
        this.zzd.zzar();
    }
}
