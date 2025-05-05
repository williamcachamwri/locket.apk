package com.google.android.play.core.appupdate;

import com.google.android.play.core.appupdate.internal.zzad;
import com.google.android.play.core.appupdate.internal.zzaf;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzz implements zza {
    private final zzz zza = this;
    private final zzaf zzb;
    private final zzaf zzc;
    private final zzaf zzd;
    private final zzaf zze;
    private final zzaf zzf;
    private final zzaf zzg;

    /* synthetic */ zzz(zzi zzi, zzy zzy) {
        zzk zzk = new zzk(zzi);
        this.zzb = zzk;
        zzaf zzb2 = zzad.zzb(new zzu(zzk));
        this.zzc = zzb2;
        zzaf zzb3 = zzad.zzb(new zzs(zzk, zzb2));
        this.zzd = zzb3;
        zzaf zzb4 = zzad.zzb(new zzd(zzk));
        this.zze = zzb4;
        zzaf zzb5 = zzad.zzb(new zzh(zzb3, zzb4, zzk));
        this.zzf = zzb5;
        this.zzg = zzad.zzb(new zzj(zzb5));
    }

    public final AppUpdateManager zza() {
        return (AppUpdateManager) this.zzg.zza();
    }
}
