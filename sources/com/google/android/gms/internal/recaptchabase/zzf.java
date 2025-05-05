package com.google.android.gms.internal.recaptchabase;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.recaptchabase.ExecuteRequest;
import com.google.android.gms.recaptchabase.InitRequest;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public final class zzf extends zza implements IInterface {
    zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.recaptchabase.internal.IRecaptchaBaseService");
    }

    public final void zzc(zze zze, ExecuteRequest executeRequest) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, zze);
        zzc.zzc(zza, executeRequest);
        zzb(2, zza);
    }

    public final void zzd(zze zze, InitRequest initRequest) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, zze);
        zzc.zzc(zza, initRequest);
        zzb(1, zza);
    }
}
