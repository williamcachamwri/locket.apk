package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzbb;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzh extends zzn {
    final /* synthetic */ zzbb zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzh(AccountTransferClient accountTransferClient, int i, zzbb zzbb) {
        super(1609);
        this.zza = zzbb;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzau zzau) throws RemoteException {
        zzau.zze(this.zzc, this.zza);
    }
}
