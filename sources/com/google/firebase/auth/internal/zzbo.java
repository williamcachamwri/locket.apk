package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzbo implements OnCompleteListener {
    private /* synthetic */ RecaptchaActivity zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzbo(RecaptchaActivity recaptchaActivity, String str) {
        this.zza = recaptchaActivity;
        this.zzb = str;
    }

    public final void onComplete(Task task) {
        this.zza.zza(this.zzb, task);
    }
}
