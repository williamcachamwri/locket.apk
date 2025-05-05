package com.google.android.gms.internal.pal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzgu extends zzfj implements zzgw {
    zzgu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.signalsdk.ISignalSdkService");
    }

    public final void zze(Bundle bundle, zzgt zzgt) throws RemoteException {
        Parcel zza = zza();
        zzfl.zzd(zza, bundle);
        zzfl.zze(zza, zzgt);
        zzv(1, zza);
    }
}
