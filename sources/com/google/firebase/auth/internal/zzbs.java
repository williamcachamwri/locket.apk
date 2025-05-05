package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzbs implements Continuation {
    private /* synthetic */ zzbq zza;

    public /* synthetic */ zzbs(zzbq zzbq) {
        this.zza = zzbq;
    }

    public final Object then(Task task) {
        zzbq zzbq = this.zza;
        if (task.isSuccessful()) {
            return zzbq.zza((String) task.getResult());
        }
        SentryLogcatAdapter.e("RecaptchaCallWrapper", "Failed to get Recaptcha token, error - " + ((Exception) Preconditions.checkNotNull(task.getException())).getMessage() + "\n\n Failing open with a fake token.");
        return zzbq.zza("NO_RECAPTCHA");
    }
}
