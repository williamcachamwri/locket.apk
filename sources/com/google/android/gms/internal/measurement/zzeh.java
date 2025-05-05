package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzeh extends zzdy.zza {
    private final /* synthetic */ zzdy zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzeh(zzdy zzdy) {
        super(zzdy);
        this.zzc = zzdy;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(this.zzc.zzj)).resetAnalyticsData(this.zza);
    }
}
