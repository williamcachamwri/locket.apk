package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzdz extends zzdy.zza {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzdy zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdz(zzdy zzdy, Bundle bundle) {
        super(zzdy);
        this.zzc = bundle;
        this.zzd = zzdy;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(this.zzd.zzj)).setConditionalUserProperty(this.zzc, this.zza);
    }
}
