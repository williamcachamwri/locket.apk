package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuthException;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzf implements OnFailureListener {
    private final /* synthetic */ TaskCompletionSource zza;

    zzf(zza zza2, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void onFailure(Exception exc) {
        SentryLogcatAdapter.e(zza.zza, String.format("Failed to get reCAPTCHA token with error [%s]- calling backend without app verification", new Object[]{exc.getMessage()}));
        if (!(exc instanceof FirebaseAuthException) || !((FirebaseAuthException) exc).getErrorCode().endsWith("UNAUTHORIZED_DOMAIN")) {
            this.zza.setResult(new zzo().zza());
        } else {
            this.zza.setException(exc);
        }
    }
}
