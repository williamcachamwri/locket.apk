package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
final class zzq {
    private final Messenger zza;
    private final zzd zzb;

    zzq(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if (Objects.equals(interfaceDescriptor, "android.os.IMessenger")) {
            this.zza = new Messenger(iBinder);
            this.zzb = null;
        } else if (Objects.equals(interfaceDescriptor, IMessengerCompat.DESCRIPTOR)) {
            this.zzb = new zzd(iBinder);
            this.zza = null;
        } else {
            SentryLogcatAdapter.w("MessengerIpcClient", "Invalid interface descriptor: ".concat(String.valueOf(interfaceDescriptor)));
            throw new RemoteException();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(Message message) throws RemoteException {
        Messenger messenger = this.zza;
        if (messenger != null) {
            messenger.send(message);
            return;
        }
        zzd zzd = this.zzb;
        if (zzd != null) {
            zzd.zzb(message);
            return;
        }
        throw new IllegalStateException("Both messengers are null");
    }
}
