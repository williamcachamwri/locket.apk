package com.google.android.gms.internal.recaptchabase;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.recaptchabase.InitRequest;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final /* synthetic */ class zzh implements RemoteCall {
    public final /* synthetic */ InitRequest zza;

    public /* synthetic */ zzh(InitRequest initRequest) {
        this.zza = initRequest;
    }

    public final void accept(Object obj, Object obj2) {
        int i = zzl.zza;
        InitRequest initRequest = this.zza;
        Intrinsics.checkNotNullParameter(initRequest, "$initRequest");
        ((zzf) ((zzm) obj).getService()).zzd(new zzk((TaskCompletionSource) obj2), initRequest);
    }
}
