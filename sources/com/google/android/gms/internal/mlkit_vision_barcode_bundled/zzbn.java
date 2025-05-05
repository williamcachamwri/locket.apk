package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public abstract class zzbn extends zzb implements zzbo {
    public zzbn() {
        super("com.google.mlkit.vision.barcode.aidls.IBarcodeScannerCreator");
    }

    public static zzbo asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.barcode.aidls.IBarcodeScannerCreator");
        return queryLocalInterface instanceof zzbo ? (zzbo) queryLocalInterface : new zzbm(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc.zzb(parcel);
        zzbl newBarcodeScanner = newBarcodeScanner(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzbc) zzc.zza(parcel, zzbc.CREATOR));
        parcel2.writeNoException();
        if (newBarcodeScanner == null) {
            parcel2.writeStrongBinder((IBinder) null);
        } else {
            parcel2.writeStrongBinder(newBarcodeScanner.asBinder());
        }
        return true;
    }
}
