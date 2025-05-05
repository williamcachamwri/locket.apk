package com.google.android.play.core.appupdate.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzk extends BroadcastReceiver {
    final /* synthetic */ zzl zza;

    /* synthetic */ zzk(zzl zzl, zzj zzj) {
        this.zza = zzl;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zza.zza(context, intent);
    }
}
