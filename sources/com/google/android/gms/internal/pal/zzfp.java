package com.google.android.gms.internal.pal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzfp extends zzfj implements zzfr {
    zzfp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.adshield.internal.IAdShieldClient");
    }

    public final int zzb() throws RemoteException {
        throw null;
    }

    public final IObjectWrapper zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        throw null;
    }

    public final IObjectWrapper zzd(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        throw null;
    }

    public final String zze(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        Parcel zza = zza();
        zzfl.zze(zza, iObjectWrapper);
        zza.writeString("");
        Parcel zzt = zzt(8, zza);
        String readString = zzt.readString();
        zzt.recycle();
        return readString;
    }

    public final String zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
        throw null;
    }

    public final String zzg(IObjectWrapper iObjectWrapper, byte[] bArr) throws RemoteException {
        Parcel zza = zza();
        zzfl.zze(zza, iObjectWrapper);
        zza.writeByteArray((byte[]) null);
        Parcel zzt = zzt(12, zza);
        String readString = zzt.readString();
        zzt.recycle();
        return readString;
    }

    public final String zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, IObjectWrapper iObjectWrapper4) throws RemoteException {
        throw null;
    }

    public final String zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        throw null;
    }

    public final String zzj() throws RemoteException {
        throw null;
    }

    public final String zzk(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel zza = zza();
        zzfl.zze(zza, iObjectWrapper);
        zzfl.zze(zza, iObjectWrapper2);
        zzfl.zze(zza, iObjectWrapper3);
        Parcel zzt = zzt(14, zza);
        String readString = zzt.readString();
        zzt.recycle();
        return readString;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzfl.zze(zza, iObjectWrapper);
        zzu(9, zza);
    }

    public final void zzm(IObjectWrapper iObjectWrapper) throws RemoteException {
        throw null;
    }

    public final void zzn(String str, String str2) throws RemoteException {
        throw null;
    }

    public final void zzo(String str) throws RemoteException {
        throw null;
    }

    public final boolean zzp(IObjectWrapper iObjectWrapper) throws RemoteException {
        throw null;
    }

    public final boolean zzq(IObjectWrapper iObjectWrapper) throws RemoteException {
        throw null;
    }

    public final boolean zzr(String str, boolean z) throws RemoteException {
        throw null;
    }
}
