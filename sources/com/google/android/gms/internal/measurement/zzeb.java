package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzeb extends zzdy.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzdk zze;
    private final /* synthetic */ zzdy zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzeb(zzdy zzdy, String str, String str2, zzdk zzdk) {
        super(zzdy);
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzdk;
        this.zzf = zzdy;
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zze.zza((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(this.zzf.zzj)).getConditionalUserProperties(this.zzc, this.zzd, this.zze);
    }
}
