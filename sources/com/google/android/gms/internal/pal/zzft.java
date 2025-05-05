package com.google.android.gms.internal.pal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public class zzft {
    protected zzfr zza;

    protected zzft() {
    }

    @Deprecated
    public final String zza(Context context, String str) throws RemoteException {
        return this.zza.zze(ObjectWrapper.wrap(context), "");
    }

    @Deprecated
    public final String zzb(Context context, byte[] bArr) throws RemoteException {
        return this.zza.zzg(ObjectWrapper.wrap(context), (byte[]) null);
    }

    public final String zzc(Context context, View view, Activity activity) throws RemoteException {
        return this.zza.zzk(ObjectWrapper.wrap(context), ObjectWrapper.wrap(null), ObjectWrapper.wrap(activity));
    }

    public final void zzd(MotionEvent motionEvent) throws RemoteException {
        this.zza.zzl(ObjectWrapper.wrap(motionEvent));
    }
}
