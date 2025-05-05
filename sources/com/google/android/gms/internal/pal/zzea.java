package com.google.android.gms.internal.pal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzea extends BroadcastReceiver {
    final /* synthetic */ zzeb zza;

    zzea(zzeb zzeb) {
        this.zza = zzeb;
    }

    public final void onReceive(Context context, Intent intent) {
        this.zza.zzf();
    }
}
