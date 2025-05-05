package com.google.ads.interactivemedia.v3.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzmy extends zzlg implements zzna {
    zzmy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.signalsdk.ISignalSdkService");
    }

    public final void zze(zznb zznb, zzmv zzmv) throws RemoteException {
        Parcel zza = zza();
        zzli.zzc(zza, zznb);
        zzli.zzd(zza, zzmv);
        zzw(2, zza);
    }

    public final void zzf(Bundle bundle, zzmx zzmx) throws RemoteException {
        Parcel zza = zza();
        zzli.zzc(zza, bundle);
        zzli.zzd(zza, zzmx);
        zzw(1, zza);
    }
}
