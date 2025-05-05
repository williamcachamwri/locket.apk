package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zzl implements Runnable {
    public final /* synthetic */ ModelResource zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzl(ModelResource modelResource, TaskCompletionSource taskCompletionSource) {
        this.zza = modelResource;
        this.zzb = taskCompletionSource;
    }

    public final void run() {
        this.zza.zzb(this.zzb);
    }
}
