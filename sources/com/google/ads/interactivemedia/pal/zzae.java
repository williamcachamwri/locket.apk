package com.google.ads.interactivemedia.pal;

import com.google.android.gms.internal.pal.zzjb;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzae implements Continuation {
    public final /* synthetic */ zzjb zza;
    public final /* synthetic */ Task zzb;
    public final /* synthetic */ Task zzc;
    public final /* synthetic */ Task zzd;
    public final /* synthetic */ Task zze;

    public /* synthetic */ zzae(zzjb zzjb, Task task, Task task2, Task task3, Task task4) {
        this.zza = zzjb;
        this.zzb = task;
        this.zzc = task2;
        this.zzd = task3;
        this.zze = task4;
    }

    public final Object then(Task task) {
        return NonceLoader.zzb(this.zza, this.zzb, this.zzc, this.zzd, this.zze, task);
    }
}
