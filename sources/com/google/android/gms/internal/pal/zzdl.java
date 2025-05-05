package com.google.android.gms.internal.pal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzdl implements Application.ActivityLifecycleCallbacks {
    private final Application zza;
    private final WeakReference zzb;
    private boolean zzc = false;

    public zzdl(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.zzb = new WeakReference(activityLifecycleCallbacks);
        this.zza = application;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(new zzdd(this, activity, bundle));
    }

    public final void onActivityDestroyed(Activity activity) {
        zza(new zzdj(this, activity));
    }

    public final void onActivityPaused(Activity activity) {
        zza(new zzdg(this, activity));
    }

    public final void onActivityResumed(Activity activity) {
        zza(new zzdf(this, activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zza(new zzdi(this, activity, bundle));
    }

    public final void onActivityStarted(Activity activity) {
        zza(new zzde(this, activity));
    }

    public final void onActivityStopped(Activity activity) {
        zza(new zzdh(this, activity));
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdk zzdk) {
        try {
            Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = (Application.ActivityLifecycleCallbacks) this.zzb.get();
            if (activityLifecycleCallbacks != null) {
                zzdk.zza(activityLifecycleCallbacks);
            } else if (!this.zzc) {
                this.zza.unregisterActivityLifecycleCallbacks(this);
                this.zzc = true;
            }
        } catch (Exception unused) {
        }
    }
}
