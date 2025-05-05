package com.google.android.play.core.appupdate.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public final /* synthetic */ class zzo implements OnCompleteListener {
    public final /* synthetic */ zzx zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzo(zzx zzx, TaskCompletionSource taskCompletionSource) {
        this.zza = zzx;
        this.zzb = taskCompletionSource;
    }

    public final void onComplete(Task task) {
        this.zza.zzt(this.zzb, task);
    }
}
