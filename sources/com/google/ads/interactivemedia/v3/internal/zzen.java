package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzen implements Application.ActivityLifecycleCallbacks {
    private final Application zza;
    private final List zzb = new ArrayList();

    public zzen(Application application) {
        this.zza = application;
        application.registerActivityLifecycleCallbacks(this);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        if (activity.isFinishing() && activity.getClass().getSimpleName().equals("FallbackImageActivity")) {
            for (zzem zzm : this.zzb) {
                zzm.zzm();
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        for (zzem zzk : this.zzb) {
            zzk.zzk();
        }
    }

    public final void onActivityResumed(Activity activity) {
        for (zzem zzl : this.zzb) {
            zzl.zzl();
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void zza(zzem zzem) {
        this.zzb.add(zzem);
    }

    public final void zzb() {
        this.zza.unregisterActivityLifecycleCallbacks(this);
        this.zzb.clear();
    }
}
