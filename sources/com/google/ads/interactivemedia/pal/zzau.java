package com.google.ads.interactivemedia.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzau implements Runnable {
    final /* synthetic */ Runnable zza;
    final /* synthetic */ zzav zzb;

    zzau(zzav zzav, Runnable runnable) {
        this.zzb = zzav;
        this.zza = runnable;
    }

    public final void run() {
        ((zzar) this.zza).zza.zzg.zza(7, ((zzar) this.zza).zza.zzk);
        zzav zzav = this.zzb;
        zzav.zza.postDelayed(this, zzav.zzb.zzd());
    }
}
