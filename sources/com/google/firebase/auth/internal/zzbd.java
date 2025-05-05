package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p002firebaseauthapi.zzadg;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import io.sentry.android.core.SentryLogcatAdapter;
import java.lang.ref.WeakReference;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzbd extends BroadcastReceiver {
    private final WeakReference<Activity> zza;
    private final TaskCompletionSource<String> zzb;
    private final /* synthetic */ zzav zzc;

    zzbd(zzav zzav, Activity activity, TaskCompletionSource<String> taskCompletionSource) {
        this.zzc = zzav;
        this.zza = new WeakReference<>(activity);
        this.zzb = taskCompletionSource;
    }

    public final void onReceive(Context context, Intent intent) {
        if (((Activity) this.zza.get()) == null) {
            SentryLogcatAdapter.e("FederatedAuthReceiver", "Failed to unregister BroadcastReceiver because the Activity that launched this flow has been garbage collected; please do not finish() your Activity while performing a FederatedAuthProvider operation.");
            this.zzb.setException(zzadg.zza(new Status((int) FirebaseError.ERROR_INTERNAL_ERROR, "Activity that started the web operation is no longer alive; see logcat for details")));
            zzav.zza(context);
        } else if (intent.hasExtra("com.google.firebase.auth.internal.OPERATION")) {
            String stringExtra = intent.getStringExtra("com.google.firebase.auth.internal.OPERATION");
            if ("com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA".equals(stringExtra)) {
                zzav.zza(this.zzc, intent, (TaskCompletionSource) this.zzb, context);
            } else {
                this.zzb.setException(zzadg.zza(zzaq.zza("WEB_CONTEXT_CANCELED:Unknown operation received (" + stringExtra + ")")));
            }
        } else if (zzcd.zzb(intent)) {
            this.zzb.setException(zzadg.zza(zzcd.zza(intent)));
            zzav.zza(context);
        } else if (intent.hasExtra("com.google.firebase.auth.internal.EXTRA_CANCELED")) {
            this.zzb.setException(zzadg.zza(zzaq.zza("WEB_CONTEXT_CANCELED")));
            zzav.zza(context);
        }
    }
}
