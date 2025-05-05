package com.google.firebase.auth.internal;

import android.app.Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzb implements OnCompleteListener {
    private /* synthetic */ zza zza;
    private /* synthetic */ TaskCompletionSource zzb;
    private /* synthetic */ FirebaseAuth zzc;
    private /* synthetic */ zzcf zzd;
    private /* synthetic */ Activity zze;

    public /* synthetic */ zzb(zza zza2, TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, zzcf zzcf, Activity activity) {
        this.zza = zza2;
        this.zzb = taskCompletionSource;
        this.zzc = firebaseAuth;
        this.zzd = zzcf;
        this.zze = activity;
    }

    public final void onComplete(Task task) {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze, task);
    }
}
