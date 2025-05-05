package com.google.android.gms.fido.u2f;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.fido.zzt;
import com.google.android.gms.internal.fido.zzu;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzc extends zzu {
    final /* synthetic */ TaskCompletionSource zza;

    zzc(U2fApiClient u2fApiClient, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(Status status, PendingIntent pendingIntent) throws RemoteException {
        TaskUtil.setResultOrApiException(status, new zzt(pendingIntent), this.zza);
    }
}
