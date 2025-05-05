package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzn extends com.google.android.play.core.appupdate.internal.zzn {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzr zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzn(zzr zzr, TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, String str) {
        super(taskCompletionSource);
        this.zzc = zzr;
        this.zza = taskCompletionSource2;
        this.zzb = str;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.play.core.appupdate.internal.zzf, android.os.IInterface] */
    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            this.zzc.zza.zze().zzc(this.zzc.zzd, zzr.zzi(), new zzp(this.zzc, this.zza));
        } catch (RemoteException e) {
            zzr.zzb.zzc(e, "completeUpdate(%s)", this.zzb);
            this.zza.trySetException(new RuntimeException(e));
        }
    }
}
