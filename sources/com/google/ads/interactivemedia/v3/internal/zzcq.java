package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import com.google.ads.interactivemedia.omid.library.adsession.zze;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcq extends zzct {
    private static final zzcq zzb = new zzcq();

    private zzcq() {
    }

    public static zzcq zza() {
        return zzb;
    }

    public final void zzb(boolean z) {
        for (zze zzh : zzcr.zza().zzc()) {
            zzh.zzh().zzk(z);
        }
    }

    public final boolean zzc() {
        for (zze zzg : zzcr.zza().zzb()) {
            View zzg2 = zzg.zzg();
            if (zzg2 != null && zzg2.hasWindowFocus()) {
                return true;
            }
        }
        return false;
    }
}
