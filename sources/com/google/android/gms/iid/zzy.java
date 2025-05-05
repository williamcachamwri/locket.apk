package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import io.sentry.android.core.SentryLogcatAdapter;

final class zzy {
    final Messenger zzad;
    final MessengerCompat zzco;

    zzy(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.zzad = new Messenger(iBinder);
            this.zzco = null;
        } else if (IMessengerCompat.DESCRIPTOR.equals(interfaceDescriptor)) {
            this.zzco = new MessengerCompat(iBinder);
            this.zzad = null;
        } else {
            String valueOf = String.valueOf(interfaceDescriptor);
            SentryLogcatAdapter.w("MessengerIpcClient", valueOf.length() != 0 ? "Invalid interface descriptor: ".concat(valueOf) : new String("Invalid interface descriptor: "));
            throw new RemoteException();
        }
    }
}
