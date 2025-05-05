package com.google.android.gms.internal.pal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzhm extends zzfj implements IInterface {
    zzhm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    public final zzhk zze(zzhi zzhi) throws RemoteException {
        Parcel zza = zza();
        zzfl.zzd(zza, zzhi);
        Parcel zzt = zzt(1, zza);
        zzhk zzhk = (zzhk) zzfl.zza(zzt, zzhk.CREATOR);
        zzt.recycle();
        return zzhk;
    }
}
