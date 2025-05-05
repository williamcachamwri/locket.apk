package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzbp implements Continuation {
    private /* synthetic */ RecaptchaAction zza;
    private /* synthetic */ FirebaseAuth zzb;
    private /* synthetic */ String zzc;
    private /* synthetic */ Continuation zzd;

    public /* synthetic */ zzbp(RecaptchaAction recaptchaAction, FirebaseAuth firebaseAuth, String str, Continuation continuation) {
        this.zza = recaptchaAction;
        this.zzb = firebaseAuth;
        this.zzc = str;
        this.zzd = continuation;
    }

    public final Object then(Task task) {
        return zzbq.zza(this.zza, this.zzb, this.zzc, this.zzd, task);
    }
}
