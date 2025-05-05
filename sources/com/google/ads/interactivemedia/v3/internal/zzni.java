package com.google.ads.interactivemedia.v3.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzni implements RemoteCall {
    public final /* synthetic */ zznm zza;
    public final /* synthetic */ Bundle zzb;

    public /* synthetic */ zzni(zznm zznm, Bundle bundle) {
        this.zza = zznm;
        this.zzb = bundle;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzna) ((zznn) obj).getService()).zzf(this.zzb, new zznk(this.zza, (TaskCompletionSource) obj2));
    }
}
