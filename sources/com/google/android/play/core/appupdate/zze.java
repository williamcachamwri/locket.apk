package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zze extends ResultReceiver {
    final /* synthetic */ TaskCompletionSource zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zze(zzg zzg, Handler handler, TaskCompletionSource taskCompletionSource) {
        super(handler);
        this.zza = taskCompletionSource;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        if (i == 1) {
            this.zza.trySetResult(-1);
        } else if (i != 2) {
            this.zza.trySetResult(1);
        } else {
            this.zza.trySetResult(0);
        }
    }
}
