package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zziy implements Application.ActivityLifecycleCallbacks {
    private final Application zza;
    private final WeakReference zzb;
    private boolean zzc = false;

    public zziy(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzb = new WeakReference(activityLifecycleCallbacks);
        this.zza = application;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zziq(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        zza(new zziw(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        zza(new zzit(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        zza(new zzis(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zziv(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        zza(new zzir(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        zza(new zziu(this, activity));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzix zzix) {
        try {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = (Application.ActivityLifecycleCallbacks) this.zzb.get();
            if (activityLifecycleCallbacks != null) {
                zzix.zza(activityLifecycleCallbacks);
            } else if (!this.zzc) {
                this.zza.unregisterActivityLifecycleCallbacks(this);
                this.zzc = true;
            }
        } catch (Exception unused) {
        }
    }
}
