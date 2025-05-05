package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-identifier@@18.2.0 */
public final class zzd extends zza implements zzf {
    zzd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    public final String zzc() throws RemoteException {
        Parcel zzb = zzb(1, zza());
        String readString = zzb.readString();
        zzb.recycle();
        return readString;
    }

    public final boolean zzd() throws RemoteException {
        Parcel zzb = zzb(6, zza());
        boolean zza = zzc.zza(zzb);
        zzb.recycle();
        return zza;
    }

    public final boolean zze(boolean z) throws RemoteException {
        Parcel zza = zza();
        int i = zzc.zza;
        zza.writeInt(1);
        Parcel zzb = zzb(2, zza);
        boolean zza2 = zzc.zza(zzb);
        zzb.recycle();
        return zza2;
    }
}
