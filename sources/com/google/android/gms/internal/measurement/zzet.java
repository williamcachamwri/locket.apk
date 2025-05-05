package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzet extends zzdy.zza {
    private final /* synthetic */ zzdy.zzc zzc;
    private final /* synthetic */ zzdy zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzet(zzdy zzdy, zzdy.zzc zzc2) {
        super(zzdy);
        this.zzc = zzc2;
        this.zzd = zzdy;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(this.zzd.zzj)).setEventInterceptor(this.zzc);
    }
}
