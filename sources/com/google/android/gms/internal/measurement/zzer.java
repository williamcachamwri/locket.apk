package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@22.1.2 */
final class zzer extends zzdy.zza {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzdk zzf;
    private final /* synthetic */ zzdy zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzer(zzdy zzdy, String str, String str2, boolean z, zzdk zzdk) {
        super(zzdy);
        this.zzc = str;
        this.zzd = str2;
        this.zze = z;
        this.zzf = zzdk;
        this.zzg = zzdy;
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        this.zzf.zza((Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        ((zzdj) Preconditions.checkNotNull(this.zzg.zzj)).getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }
}
