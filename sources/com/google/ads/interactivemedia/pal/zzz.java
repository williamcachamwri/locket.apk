package com.google.ads.interactivemedia.pal;

import com.google.android.gms.internal.pal.zzjb;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzz implements Continuation {
    public final /* synthetic */ NonceLoader zza;
    public final /* synthetic */ zzjb zzb;
    public final /* synthetic */ Task zzc;
    public final /* synthetic */ Task zzd;
    public final /* synthetic */ Task zze;
    public final /* synthetic */ NonceRequest zzf;
    public final /* synthetic */ String zzg;
    public final /* synthetic */ long zzh;

    public /* synthetic */ zzz(NonceLoader nonceLoader, zzjb zzjb, Task task, Task task2, Task task3, NonceRequest nonceRequest, String str, long j) {
        this.zza = nonceLoader;
        this.zzb = zzjb;
        this.zzc = task;
        this.zzd = task2;
        this.zze = task3;
        this.zzf = nonceRequest;
        this.zzg = str;
        this.zzh = j;
    }

    public final Object then(Task task) {
        return this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, task);
    }
}
