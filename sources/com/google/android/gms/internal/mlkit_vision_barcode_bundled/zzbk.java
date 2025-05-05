package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public abstract class zzbk extends zzb implements zzbl {
    public zzbk() {
        super("com.google.mlkit.vision.barcode.aidls.IBarcodeScanner");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc();
            parcel2.writeNoException();
        } else if (i == 2) {
            zzd();
            parcel2.writeNoException();
        } else if (i != 3) {
            return false;
        } else {
            zzc.zzb(parcel);
            List zzb = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzbu) zzc.zza(parcel, zzbu.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedList(zzb);
        }
        return true;
    }
}
