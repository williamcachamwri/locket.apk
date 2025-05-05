package com.google.android.play.core.appupdate;

import android.content.Context;
import com.google.android.play.core.appupdate.internal.zzz;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public final class zzb {
    private static zza zza;

    static synchronized zza zza(Context context) {
        zza zza2;
        synchronized (zzb.class) {
            if (zza == null) {
                zzab zzab = new zzab((zzaa) null);
                zzab.zzb(new zzi(zzz.zza(context)));
                zza = zzab.zza();
            }
            zza2 = zza;
        }
        return zza2;
    }
}
