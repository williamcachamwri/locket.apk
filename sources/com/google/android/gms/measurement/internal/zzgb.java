package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public interface zzgb extends IInterface {
    zzaj zza(zzo zzo) throws RemoteException;

    List<zzno> zza(zzo zzo, Bundle bundle) throws RemoteException;

    List<zzon> zza(zzo zzo, boolean z) throws RemoteException;

    List<zzae> zza(String str, String str2, zzo zzo) throws RemoteException;

    List<zzae> zza(String str, String str2, String str3) throws RemoteException;

    List<zzon> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzon> zza(String str, String str2, boolean z, zzo zzo) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(Bundle bundle, zzo zzo) throws RemoteException;

    void zza(zzae zzae) throws RemoteException;

    void zza(zzae zzae, zzo zzo) throws RemoteException;

    void zza(zzbf zzbf, zzo zzo) throws RemoteException;

    void zza(zzbf zzbf, String str, String str2) throws RemoteException;

    void zza(zzon zzon, zzo zzo) throws RemoteException;

    byte[] zza(zzbf zzbf, String str) throws RemoteException;

    String zzb(zzo zzo) throws RemoteException;

    void zzb(Bundle bundle, zzo zzo) throws RemoteException;

    void zzc(zzo zzo) throws RemoteException;

    void zzd(zzo zzo) throws RemoteException;

    void zze(zzo zzo) throws RemoteException;

    void zzf(zzo zzo) throws RemoteException;

    void zzg(zzo zzo) throws RemoteException;

    void zzh(zzo zzo) throws RemoteException;

    void zzi(zzo zzo) throws RemoteException;
}
