package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzed extends zzdy.zza {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zzdy zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzed(zzdy zzdy, Activity activity, String str, String str2) {
        super(zzdy);
        this.zzc = activity;
        this.zzd = str;
        this.zze = str2;
        this.zzf = zzdy;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(this.zzf.zzj)).setCurrentScreen(ObjectWrapper.wrap(this.zzc), this.zzd, this.zze, this.zza);
    }
}
