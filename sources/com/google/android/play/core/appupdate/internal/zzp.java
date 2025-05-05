package com.google.android.play.core.appupdate.internal;

import android.os.IBinder;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public final /* synthetic */ class zzp implements IBinder.DeathRecipient {
    public final /* synthetic */ zzx zza;

    public /* synthetic */ zzp(zzx zzx) {
        this.zza = zzx;
    }

    public final void binderDied() {
        zzx.zzj(this.zza);
    }
}
