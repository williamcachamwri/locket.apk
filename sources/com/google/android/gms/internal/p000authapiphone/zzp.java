package com.google.android.gms.internal.p000authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzp  reason: invalid package */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@18.0.2 */
final class zzp extends zzd {
    final /* synthetic */ TaskCompletionSource zza;

    zzp(zzr zzr, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(Status status, int i) {
        TaskUtil.setResultOrApiException(status, Integer.valueOf(i), this.zza);
    }
}
