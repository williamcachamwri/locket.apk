package com.google.android.gms.internal.recaptchabase;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.recaptchabase.ExecuteRequest;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final /* synthetic */ class zzg implements RemoteCall {
    public final /* synthetic */ ExecuteRequest zza;

    public /* synthetic */ zzg(ExecuteRequest executeRequest) {
        this.zza = executeRequest;
    }

    public final void accept(Object obj, Object obj2) {
        int i = zzl.zza;
        ExecuteRequest executeRequest = this.zza;
        Intrinsics.checkNotNullParameter(executeRequest, "$executeRequest");
        ((zzf) ((zzm) obj).getService()).zzc(new zzj((TaskCompletionSource) obj2), executeRequest);
    }
}
