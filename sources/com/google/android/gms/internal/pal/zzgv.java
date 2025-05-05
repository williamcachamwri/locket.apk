package com.google.android.gms.internal.pal;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzgv extends zzfk implements zzgw {
    public static zzgw zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.signalsdk.ISignalSdkService");
        return queryLocalInterface instanceof zzgw ? (zzgw) queryLocalInterface : new zzgu(iBinder);
    }
}
