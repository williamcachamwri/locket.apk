package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.appupdate.internal.zzm;
import com.google.android.play.core.install.InstallException;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzp extends zzo {
    zzp(zzr zzr, TaskCompletionSource taskCompletionSource) {
        super(zzr, new zzm("OnCompleteUpdateCallback"), taskCompletionSource);
    }

    public final void zzb(Bundle bundle) throws RemoteException {
        super.zzb(bundle);
        if (bundle.getInt("error.code", -2) != 0) {
            this.zzb.trySetException(new InstallException(bundle.getInt("error.code", -2)));
        } else {
            this.zzb.trySetResult(null);
        }
    }
}
