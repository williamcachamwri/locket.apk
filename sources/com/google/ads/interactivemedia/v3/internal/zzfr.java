package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.signals.SecureSignalsInitializeCallback;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzfr implements SecureSignalsInitializeCallback {
    final /* synthetic */ zzft zza;

    zzfr(zzft zzft) {
        this.zza = zzft;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzd.trySetException(exc);
    }

    public final void onSuccess() {
        this.zza.zzd.trySetResult(null);
    }
}
