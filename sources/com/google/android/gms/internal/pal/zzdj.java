package com.google.android.gms.internal.pal;

import android.app.Activity;
import android.app.Application;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzdj implements zzdk {
    final /* synthetic */ Activity zza;

    zzdj(zzdl zzdl, Activity activity) {
        this.zza = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityDestroyed(this.zza);
    }
}
