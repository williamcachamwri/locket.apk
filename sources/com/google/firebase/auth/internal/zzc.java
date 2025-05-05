package com.google.firebase.auth.internal;

import android.app.Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzc implements OnCompleteListener {
    private /* synthetic */ zza zza;
    private /* synthetic */ TaskCompletionSource zzb;
    private /* synthetic */ FirebaseAuth zzc;
    private /* synthetic */ RecaptchaAction zzd;
    private /* synthetic */ String zze;
    private /* synthetic */ Activity zzf;
    private /* synthetic */ boolean zzg;
    private /* synthetic */ boolean zzh;
    private /* synthetic */ zzcf zzi;

    public /* synthetic */ zzc(zza zza2, TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, RecaptchaAction recaptchaAction, String str, Activity activity, boolean z, boolean z2, zzcf zzcf) {
        this.zza = zza2;
        this.zzb = taskCompletionSource;
        this.zzc = firebaseAuth;
        this.zzd = recaptchaAction;
        this.zze = str;
        this.zzf = activity;
        this.zzg = z;
        this.zzh = z2;
        this.zzi = zzcf;
    }

    public final void onComplete(Task task) {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, task);
    }
}
