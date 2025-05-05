package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzgc implements Continuation {
    public final /* synthetic */ zzgd zza;

    public /* synthetic */ zzgc(zzgd zzgd) {
        this.zza = zzgd;
    }

    public final Object then(Task task) {
        Void unused = this.zza.zzc.trySetResult(this.zza.zza);
        return null;
    }
}
