package com.google.android.gms.internal.p000authapiphone;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzo  reason: invalid package */
/* compiled from: com.google.android.gms:play-services-auth-api-phone@@18.0.2 */
final class zzo extends IStatusCallback.Stub {
    final /* synthetic */ TaskCompletionSource zza;

    zzo(zzr zzr, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void onResult(Status status) {
        if (status.getStatusCode() == 6) {
            this.zza.trySetException(ApiExceptionUtil.fromStatus(status));
        } else {
            TaskUtil.setResultOrApiException(status, this.zza);
        }
    }
}
