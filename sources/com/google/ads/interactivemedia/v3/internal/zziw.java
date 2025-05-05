package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.app.Application;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zziw implements zzix {
    final /* synthetic */ Activity zza;

    zziw(zziy zziy, Activity activity) {
        this.zza = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityDestroyed(this.zza);
    }
}
