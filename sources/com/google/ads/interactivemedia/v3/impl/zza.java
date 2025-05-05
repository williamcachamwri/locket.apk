package com.google.ads.interactivemedia.v3.impl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zza implements Application.ActivityLifecycleCallbacks {
    final /* synthetic */ zzb zza;

    zza(zzb zzb) {
        this.zza = zzb;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        zzb zzb = this.zza;
        if (zzb.zze == activity) {
            zzb.zze = null;
            this.zza.zzi();
        }
    }

    public final void onActivityPaused(Activity activity) {
        zzb zzb = this.zza;
        if (zzb.zze == null || zzb.zze == activity) {
            zzb.zze = activity;
            zzb zzb2 = this.zza;
            zzb2.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.activityMonitor, JavaScriptMessage.MsgType.appStateChanged, zzb2.zzb, zzb2.zzc("", "", "inactive")));
        }
    }

    public final void onActivityResumed(Activity activity) {
        zzb zzb = this.zza;
        if (zzb.zze == activity) {
            zzb.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.activityMonitor, JavaScriptMessage.MsgType.appStateChanged, zzb.zzb, zzb.zzc("", "", "active")));
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
