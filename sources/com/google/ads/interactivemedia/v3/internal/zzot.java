package com.google.ads.interactivemedia.v3.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzot extends zzlg implements IInterface {
    zzot(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    public final zzor zze(zzop zzop) throws RemoteException {
        Parcel zza = zza();
        zzli.zzc(zza, zzop);
        Parcel zzu = zzu(1, zza);
        zzor zzor = (zzor) zzli.zza(zzu, zzor.CREATOR);
        zzu.recycle();
        return zzor;
    }

    public final zzpa zzf(zzoy zzoy) throws RemoteException {
        Parcel zza = zza();
        zzli.zzc(zza, zzoy);
        Parcel zzu = zzu(3, zza);
        zzpa zzpa = (zzpa) zzli.zza(zzu, zzpa.CREATOR);
        zzu.recycle();
        return zzpa;
    }
}
