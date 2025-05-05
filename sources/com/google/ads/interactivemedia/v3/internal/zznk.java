package com.google.ads.interactivemedia.v3.internal;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zznk extends zzmw {
    final /* synthetic */ TaskCompletionSource zza;

    zznk(zznm zznm, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(int i) {
        this.zza.trySetException(new zzng(i));
    }

    public final void zzc(Bundle bundle) {
        this.zza.trySetResult(bundle.getString("newToken"));
    }
}
