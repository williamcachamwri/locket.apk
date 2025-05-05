package com.google.android.gms.internal.pal;

import android.os.Bundle;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzgz implements RemoteCall {
    public final /* synthetic */ zzhc zza;
    public final /* synthetic */ Bundle zzb;

    public /* synthetic */ zzgz(zzhc zzhc, Bundle bundle) {
        this.zza = zzhc;
        this.zzb = bundle;
    }

    public final void accept(Object obj, Object obj2) {
        zzhc zzhc = this.zza;
        ((zzgw) ((zzhd) obj).getService()).zze(this.zzb, new zzhb(zzhc, (TaskCompletionSource) obj2));
    }
}
