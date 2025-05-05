package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzpr {
    public static zzuu zza(Task task, CancellationTokenSource cancellationTokenSource) {
        zzpq zzpq = new zzpq(task, (Runnable) null);
        task.addOnCompleteListener(zzvb.zzb(), new zzpp(zzpq));
        return zzpq;
    }
}
