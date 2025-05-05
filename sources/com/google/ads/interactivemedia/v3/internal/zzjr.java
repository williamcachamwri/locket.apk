package com.google.ads.interactivemedia.v3.internal;

import android.app.AppOpsManager;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzjr implements AppOpsManager.OnOpActiveChangedListener {
    final /* synthetic */ zzjs zza;

    zzjr(zzjs zzjs) {
        this.zza = zzjs;
    }

    public final void onOpActiveChanged(String str, int i, String str2, boolean z) {
        if (z) {
            this.zza.zzb = System.currentTimeMillis();
            this.zza.zze = true;
            return;
        }
        zzjs zzjs = this.zza;
        long currentTimeMillis = System.currentTimeMillis();
        if (zzjs.zzc > 0) {
            zzjs zzjs2 = this.zza;
            if (currentTimeMillis >= zzjs2.zzc) {
                zzjs2.zzd = currentTimeMillis - zzjs2.zzc;
            }
        }
        this.zza.zze = false;
    }
}
