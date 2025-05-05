package com.google.android.gms.internal.pal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzhr extends zzfk implements zzhs {
    public zzhr() {
        super("com.google.android.gms.gass.internal.clearcut.IGassClearcut");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 2:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                parcel.readString();
                zzfl.zzb(parcel);
                break;
            case 3:
                break;
            case 4:
                parcel.createIntArray();
                zzfl.zzb(parcel);
                break;
            case 5:
                parcel.createByteArray();
                zzfl.zzb(parcel);
                break;
            case 6:
                parcel.readInt();
                zzfl.zzb(parcel);
                break;
            case 7:
                parcel.readInt();
                zzfl.zzb(parcel);
                break;
            case 8:
                IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                parcel.readString();
                parcel.readString();
                zzfl.zzb(parcel);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
