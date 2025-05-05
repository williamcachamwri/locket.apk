package com.google.firebase.auth;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzh;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzk implements OnCompleteListener<zzh> {
    private final /* synthetic */ PhoneAuthOptions zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    zzk(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, String str) {
        this.zza = phoneAuthOptions;
        this.zzb = str;
        this.zzc = firebaseAuth;
    }

    public final void onComplete(Task<zzh> task) {
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            SentryLogcatAdapter.e("FirebaseAuth", "Error while validating application identity: " + (exception != null ? exception.getMessage() : ""));
            if (exception == null || !zza.zza(exception)) {
                SentryLogcatAdapter.e("FirebaseAuth", "Proceeding without any application identifier.");
            } else {
                FirebaseAuth.zza((FirebaseException) exception, this.zza, this.zzb);
                return;
            }
        }
        this.zzc.zza(this.zza, task.getResult());
    }
}
