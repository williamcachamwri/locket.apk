package com.google.android.gms.internal.measurement;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public class zzbx extends Binder implements IInterface {
    public IBinder asBinder() {
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        return false;
    }

    protected zzbx(String str) {
        attachInterface(this, str);
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        boolean z;
        if (i > 16777215) {
            z = super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
            z = false;
        }
        if (z) {
            return true;
        }
        return zza(i, parcel, parcel2, i2);
    }
}
