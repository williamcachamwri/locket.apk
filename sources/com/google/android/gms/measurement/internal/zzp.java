package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzpn;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzp extends BroadcastReceiver {
    private final zzhy zza;

    public zzp(zzhy zzhy) {
        this.zza = zzhy;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            this.zza.zzj().zzu().zza("App receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            this.zza.zzj().zzu().zza("App receiver called with null action");
            return;
        }
        action.hashCode();
        if (!action.equals("com.google.android.gms.measurement.TRIGGERS_AVAILABLE")) {
            this.zza.zzj().zzu().zza("App receiver called with unknown action");
            return;
        }
        zzhy zzhy = this.zza;
        if (zzpn.zza() && zzhy.zzf().zzf((String) null, zzbh.zzci)) {
            zzhy.zzj().zzp().zza("App receiver notified triggers are available");
            zzhy.zzl().zzb((Runnable) new zzr(zzhy));
        }
    }
}
