package com.google.ads.interactivemedia.v3.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzmu extends zzlh implements zzmv {
    public zzmu() {
        super("com.google.android.gms.ads.signalsdk.INetworkRequestCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzli.zzb(parcel);
            zzc((zznd) zzli.zza(parcel, zznd.CREATOR));
        } else if (i != 2) {
            return false;
        } else {
            int readInt = parcel.readInt();
            zzli.zzb(parcel);
            zzb(readInt);
        }
        return true;
    }
}
