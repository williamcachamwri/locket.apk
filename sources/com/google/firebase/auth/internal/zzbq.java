package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzadg;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuth;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzbq<T> {
    public abstract Task<T> zza(String str);

    private static <T> Task<T> zza(zzbv zzbv, RecaptchaAction recaptchaAction, String str, Continuation<String, Task<T>> continuation) {
        Task<String> zza = zzbv.zza(str, false, recaptchaAction);
        return zza.continueWithTask(continuation).continueWithTask(new zzbr(str, zzbv, recaptchaAction, continuation));
    }

    public final Task<T> zza(FirebaseAuth firebaseAuth, String str, RecaptchaAction recaptchaAction, String str2) {
        zzbs zzbs = new zzbs(this);
        zzbv zzb = firebaseAuth.zzb();
        if (zzb == null || !zzb.zzb(str2)) {
            return zza((String) null).continueWithTask(new zzbp(recaptchaAction, firebaseAuth, str, zzbs));
        }
        return zza(zzb, recaptchaAction, str, zzbs);
    }

    static /* synthetic */ Task zza(RecaptchaAction recaptchaAction, FirebaseAuth firebaseAuth, String str, Continuation continuation, Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(task.getResult());
        }
        Exception exc = (Exception) Preconditions.checkNotNull(task.getException());
        if (zzadg.zzd(exc)) {
            if (Log.isLoggable("RecaptchaCallWrapper", 4)) {
                Log.i("RecaptchaCallWrapper", "Falling back to recaptcha enterprise flow for action " + String.valueOf(recaptchaAction));
            }
            if (firebaseAuth.zzb() == null) {
                firebaseAuth.zza(new zzbv(firebaseAuth.getApp(), firebaseAuth));
            }
            return zza(firebaseAuth.zzb(), recaptchaAction, str, continuation);
        }
        String valueOf = String.valueOf(recaptchaAction);
        SentryLogcatAdapter.e("RecaptchaCallWrapper", "Initial task failed for action " + valueOf + "with exception - " + exc.getMessage());
        return Tasks.forException(exc);
    }
}
