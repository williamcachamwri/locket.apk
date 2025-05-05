package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.p002firebaseauthapi.zzahr;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zze;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzav {
    private static zzav zza;
    private boolean zzb = false;
    private BroadcastReceiver zzc;

    private static AuthCredential zza(Intent intent) {
        Preconditions.checkNotNull(intent);
        return zze.zza(((zzahr) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST", zzahr.CREATOR)).zzc(true));
    }

    public static zzav zza() {
        if (zza == null) {
            zza = new zzav();
        }
        return zza;
    }

    static /* synthetic */ void zza(zzav zzav, Intent intent, TaskCompletionSource taskCompletionSource, Context context) {
        taskCompletionSource.setResult(intent.getStringExtra("com.google.firebase.auth.internal.RECAPTCHA_TOKEN"));
        zza(context);
    }

    private zzav() {
    }

    static void zza(Context context) {
        zzav zzav = zza;
        zzav.zzb = false;
        if (zzav.zzc != null) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(zza.zzc);
        }
        zza.zzc = null;
    }

    private final void zza(Activity activity, BroadcastReceiver broadcastReceiver) {
        this.zzc = broadcastReceiver;
        LocalBroadcastManager.getInstance(activity).registerReceiver(broadcastReceiver, new IntentFilter("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT"));
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth) {
        return zza(activity, taskCompletionSource, firebaseAuth, (FirebaseUser) null);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        if (this.zzb) {
            return false;
        }
        zza(activity, (BroadcastReceiver) new zzbe(this, activity, taskCompletionSource, firebaseAuth, firebaseUser));
        this.zzb = true;
        return true;
    }

    public final boolean zza(Activity activity, TaskCompletionSource<String> taskCompletionSource) {
        if (this.zzb) {
            return false;
        }
        zza(activity, (BroadcastReceiver) new zzbd(this, activity, taskCompletionSource));
        this.zzb = true;
        return true;
    }
}
