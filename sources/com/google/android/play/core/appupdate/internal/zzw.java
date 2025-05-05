package com.google.android.play.core.appupdate.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzw implements ServiceConnection {
    final /* synthetic */ zzx zza;

    /* synthetic */ zzw(zzx zzx, zzv zzv) {
        this.zza = zzx;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        zzx zzx = this.zza;
        zzx.zzc().post(new zzt(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zzc.zzd("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        zzx zzx = this.zza;
        zzx.zzc().post(new zzu(this));
    }
}
