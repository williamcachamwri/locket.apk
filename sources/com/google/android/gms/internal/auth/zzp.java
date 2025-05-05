package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.common.api.internal.IStatusCallback;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public final class zzp extends zza implements IInterface {
    zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.data.IGoogleAuthService");
    }

    public final void zzd(IStatusCallback iStatusCallback, zzbw zzbw) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, iStatusCallback);
        zzc.zzc(zza, zzbw);
        zzc(2, zza);
    }

    public final void zze(zzm zzm, AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, zzm);
        zzc.zzc(zza, accountChangeEventsRequest);
        zzc(4, zza);
    }

    public final void zzf(zzo zzo, Account account, String str, Bundle bundle) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, zzo);
        zzc.zzc(zza, account);
        zza.writeString(str);
        zzc.zzc(zza, bundle);
        zzc(1, zza);
    }

    public final void zzg(zzk zzk, Account account) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, zzk);
        zzc.zzc(zza, account);
        zzc(6, zza);
    }

    public final void zzh(zzk zzk, String str) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, zzk);
        zza.writeString(str);
        zzc(3, zza);
    }
}
