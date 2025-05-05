package com.google.android.gms.internal.recaptchabase;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptchabase.ExecuteResult;
import com.google.android.gms.recaptchabase.InitResult;

/* compiled from: com.google.android.gms:play-services-recaptchabase@@16.1.0 */
public abstract class zzd extends zzb implements zze {
    public zzd() {
        super("com.google.android.gms.recaptchabase.internal.IRecaptchaBaseCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc.zzb(parcel);
            zzc((Status) zzc.zza(parcel, Status.CREATOR), (InitResult) zzc.zza(parcel, InitResult.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            zzc.zzb(parcel);
            zzb((Status) zzc.zza(parcel, Status.CREATOR), (ExecuteResult) zzc.zza(parcel, ExecuteResult.CREATOR));
        }
        return true;
    }
}
