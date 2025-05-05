package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zza;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public final class zzx extends zza implements ICancelToken {
    zzx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICancelToken");
    }

    public final void cancel() throws RemoteException {
        zzD(2, zza());
    }
}
