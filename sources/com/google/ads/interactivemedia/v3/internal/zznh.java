package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zznh implements RemoteCall {
    public final /* synthetic */ zznm zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ int zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zznh(zznm zznm, String str, int i, String str2) {
        this.zza = zznm;
        this.zzb = str;
        this.zzc = i;
        this.zzd = str2;
    }

    public final void accept(Object obj, Object obj2) {
        ((zzna) ((zznn) obj).getService()).zze(new zznb(this.zzb, this.zzc, this.zzd), new zznl(this.zza, (TaskCompletionSource) obj2));
    }
}
