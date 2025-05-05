package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.appupdate.internal.zzm;
import com.google.android.play.core.install.InstallException;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzq extends zzo {
    final /* synthetic */ zzr zzd;
    private final String zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzq(zzr zzr, TaskCompletionSource taskCompletionSource, String str) {
        super(zzr, new zzm("OnRequestInstallCallback"), taskCompletionSource);
        this.zzd = zzr;
        this.zze = str;
    }

    public final void zzc(Bundle bundle) throws RemoteException {
        super.zzc(bundle);
        if (bundle.getInt("error.code", -2) != 0) {
            this.zzb.trySetException(new InstallException(bundle.getInt("error.code", -2)));
        } else {
            this.zzb.trySetResult(zzr.zzf(this.zzd, bundle, this.zze));
        }
    }
}
