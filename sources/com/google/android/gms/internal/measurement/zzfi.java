package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzfi extends zzdy.zza {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzdy.zzd zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfi(zzdy.zzd zzd2, Activity activity) {
        super(zzdy.this);
        this.zzc = activity;
        this.zzd = zzd2;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(zzdy.this.zzj)).onActivityStarted(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
