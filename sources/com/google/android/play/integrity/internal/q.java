package com.google.android.play.integrity.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public abstract class q extends b implements r {
    public q() {
        super("com.google.android.play.core.integrity.protocol.IRequestDialogCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        c.b(parcel);
        b((Bundle) c.a(parcel, Bundle.CREATOR));
        return true;
    }
}
