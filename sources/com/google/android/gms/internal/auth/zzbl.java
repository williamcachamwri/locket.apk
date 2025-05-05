package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public final /* synthetic */ class zzbl implements RemoteCall {
    public final /* synthetic */ zzbo zza;
    public final /* synthetic */ ProxyRequest zzb;

    public /* synthetic */ zzbl(zzbo zzbo, ProxyRequest proxyRequest) {
        this.zza = zzbo;
        this.zzb = proxyRequest;
    }

    public final void accept(Object obj, Object obj2) {
        zzbo zzbo = this.zza;
        ProxyRequest proxyRequest = this.zzb;
        ((zzbh) ((zzbe) obj).getService()).zze(new zzbm(zzbo, (TaskCompletionSource) obj2), proxyRequest);
    }
}
