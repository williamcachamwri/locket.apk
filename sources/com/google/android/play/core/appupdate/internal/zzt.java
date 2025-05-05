package com.google.android.play.core.appupdate.internal;

import android.os.IBinder;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzt extends zzn {
    final /* synthetic */ IBinder zza;
    final /* synthetic */ zzw zzb;

    zzt(zzw zzw, IBinder iBinder) {
        this.zzb = zzw;
        this.zza = iBinder;
    }

    public final void zza() {
        this.zzb.zza.zzn = zze.zzb(this.zza);
        zzx.zzq(this.zzb.zza);
        this.zzb.zza.zzh = false;
        for (Runnable run : this.zzb.zza.zze) {
            run.run();
        }
        this.zzb.zza.zze.clear();
    }
}
