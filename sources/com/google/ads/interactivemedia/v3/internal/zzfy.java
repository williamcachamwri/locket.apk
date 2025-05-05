package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzfy implements Continuation {
    public final /* synthetic */ zzgd zza;

    public /* synthetic */ zzfy(zzgd zzgd) {
        this.zza = zzgd;
    }

    public final Object then(Task task) {
        return zzgd.zza(this.zza, task);
    }
}
