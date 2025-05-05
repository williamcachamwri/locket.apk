package com.google.android.play.core.appupdate;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.appupdate.internal.zzg;
import com.google.android.play.core.appupdate.internal.zzm;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
class zzo extends zzg {
    final zzm zza;
    final TaskCompletionSource zzb;
    final /* synthetic */ zzr zzc;

    zzo(zzr zzr, zzm zzm, TaskCompletionSource taskCompletionSource) {
        this.zzc = zzr;
        this.zza = zzm;
        this.zzb = taskCompletionSource;
    }

    public void zzb(Bundle bundle) throws RemoteException {
        this.zzc.zza.zzu(this.zzb);
        this.zza.zzd("onCompleteUpdate", new Object[0]);
    }

    public void zzc(Bundle bundle) throws RemoteException {
        this.zzc.zza.zzu(this.zzb);
        this.zza.zzd("onRequestInfo", new Object[0]);
    }
}
