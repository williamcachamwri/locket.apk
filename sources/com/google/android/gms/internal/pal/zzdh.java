package com.google.android.gms.internal.pal;

import android.app.Activity;
import android.app.Application;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzdh implements zzdk {
    final /* synthetic */ Activity zza;

    zzdh(zzdl zzdl, Activity activity) {
        this.zza = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStopped(this.zza);
    }
}
