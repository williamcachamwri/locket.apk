package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.signals.SecureSignalsCollectSignalsCallback;
import com.google.ads.interactivemedia.v3.impl.data.zzcf;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzfs implements SecureSignalsCollectSignalsCallback {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzft zzb;

    zzfs(zzft zzft, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
        this.zzb = zzft;
    }

    public final void onFailure(Exception exc) {
        this.zza.trySetException(exc);
    }

    public final void onSuccess(String str) {
        this.zza.trySetResult(zzcf.createBy3rdPartyData(this.zzb.zza.getVersion(), this.zzb.zza.getSDKVersion(), this.zzb.zze(), str));
    }
}
