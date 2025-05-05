package com.google.android.gms.internal.pal;

import android.app.AppOpsManager;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzec implements AppOpsManager.OnOpActiveChangedListener {
    final /* synthetic */ zzed zza;

    zzec(zzed zzed) {
        this.zza = zzed;
    }

    public final void onOpActiveChanged(String str, int i, String str2, boolean z) {
        if (z) {
            this.zza.zzb = System.currentTimeMillis();
            this.zza.zze = true;
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        zzed zzed = this.zza;
        if (zzed.zzc > 0 && currentTimeMillis >= zzed.zzc) {
            zzed.zzd = currentTimeMillis - zzed.zzc;
        }
        this.zza.zze = false;
    }
}
