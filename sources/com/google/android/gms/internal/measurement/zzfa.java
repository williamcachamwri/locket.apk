package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzfa extends zzdy.zza {
    private final /* synthetic */ zzdk zzc;
    private final /* synthetic */ int zzd;
    private final /* synthetic */ zzdy zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfa(zzdy zzdy, zzdk zzdk, int i) {
        super(zzdy);
        this.zzc = zzdk;
        this.zzd = i;
        this.zze = zzdy;
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(this.zze.zzj)).getTestFlag(this.zzc, this.zzd);
    }
}
