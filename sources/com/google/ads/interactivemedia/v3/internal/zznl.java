package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zznl extends zzmu {
    final /* synthetic */ TaskCompletionSource zza;

    zznl(zznm zznm, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(int i) {
        this.zza.trySetException(new zzng(i));
    }

    public final void zzc(zznd zznd) {
        this.zza.trySetResult(zznd.zza());
    }
}
