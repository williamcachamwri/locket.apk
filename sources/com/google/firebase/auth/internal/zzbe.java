package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p002firebaseauthapi.zzadg;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import io.sentry.android.core.SentryLogcatAdapter;
import java.lang.ref.WeakReference;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzbe extends BroadcastReceiver {
    private final WeakReference<Activity> zza;
    private final TaskCompletionSource<AuthResult> zzb;
    private final FirebaseAuth zzc;
    private final FirebaseUser zzd;
    private final /* synthetic */ zzav zze;

    zzbe(zzav zzav, Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zze = zzav;
        this.zza = new WeakReference<>(activity);
        this.zzb = taskCompletionSource;
        this.zzc = firebaseAuth;
        this.zzd = firebaseUser;
    }

    public final void onReceive(Context context, Intent intent) {
        if (((Activity) this.zza.get()) == null) {
            SentryLogcatAdapter.e("FederatedAuthReceiver", "Failed to unregister BroadcastReceiver because the Activity that launched this flow has been garbage collected; please do not finish() your Activity while performing a FederatedAuthProvider operation.");
            this.zzb.setException(zzadg.zza(new Status((int) FirebaseError.ERROR_INTERNAL_ERROR, "Activity that started the web operation is no longer alive; see logcat for details")));
            zzav.zza(context);
        } else if (intent.hasExtra("com.google.firebase.auth.internal.OPERATION")) {
            String stringExtra = intent.getStringExtra("com.google.firebase.auth.internal.OPERATION");
            if ("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN".equals(stringExtra)) {
                this.zzc.signInWithCredential(zzav.zza(intent)).addOnSuccessListener(new zzax(this.zze, this.zzb, context)).addOnFailureListener(new zzay(this.zze, this.zzb, context));
            } else if ("com.google.firebase.auth.internal.NONGMSCORE_LINK".equals(stringExtra)) {
                this.zzd.linkWithCredential(zzav.zza(intent)).addOnSuccessListener(new zzaz(this.zze, this.zzb, context)).addOnFailureListener(new zzba(this.zze, this.zzb, context));
            } else if ("com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE".equals(stringExtra)) {
                this.zzd.reauthenticateAndRetrieveData(zzav.zza(intent)).addOnSuccessListener(new zzbb(this.zze, this.zzb, context)).addOnFailureListener(new zzbc(this.zze, this.zzb, context));
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
