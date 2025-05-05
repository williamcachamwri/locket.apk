package com.google.android.gms.internal.pal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public interface zzfr extends IInterface {
    int zzb() throws RemoteException;

    IObjectWrapper zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    IObjectWrapper zzd(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    String zze(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    String zzf(IObjectWrapper iObjectWrapper) throws RemoteException;

    String zzg(IObjectWrapper iObjectWrapper, byte[] bArr) throws RemoteException;

    String zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, IObjectWrapper iObjectWrapper4) throws RemoteException;

    String zzi(IObjectWrapper iObjectWrapper) throws RemoteException;

    String zzj() throws RemoteException;

    String zzk(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    void zzl(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzm(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzn(String str, String str2) throws RemoteException;

    void zzo(String str) throws RemoteException;

    boolean zzp(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzq(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzr(String str, boolean z) throws RemoteException;
}
