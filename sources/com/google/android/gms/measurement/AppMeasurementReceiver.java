package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzhj;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzhj.zza {
    private zzhj zza;

    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    public final void doStartService(Context context, Intent intent) {
        startWakefulService(context, intent);
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zza == null) {
            this.zza = new zzhj(this);
        }
        this.zza.zza(context, intent);
    }
}
