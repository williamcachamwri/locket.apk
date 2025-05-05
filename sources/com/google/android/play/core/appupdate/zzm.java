package com.google.android.play.core.appupdate;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.appupdate.internal.zzn;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzm extends zzn {
    final /* synthetic */ String zza;
    final /* synthetic */ TaskCompletionSource zzb;
    final /* synthetic */ zzr zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzm(zzr zzr, TaskCompletionSource taskCompletionSource, String str, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zzc = zzr;
        this.zza = str;
        this.zzb = taskCompletionSource2;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.play.core.appupdate.internal.zzf, android.os.IInterface] */
    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ? zze = this.zzc.zza.zze();
            zzr zzr = this.zzc;
            zze.zzd(zzr.zzd, zzr.zzb(zzr, this.zza), new zzq(this.zzc, this.zzb, this.zza));
        } catch (RemoteException e) {
            zzr.zzb.zzc(e, "requestUpdateInfo(%s)", this.zza);
            this.zzb.trySetException(new RuntimeException(e));
        }
    }
}
