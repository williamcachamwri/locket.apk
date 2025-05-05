package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import java.util.concurrent.Executor;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzll extends RemoteCreator {
    private static final zzll zza = new zzll();

    private zzll() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }

    public static zzlo zza(Context context, Executor executor, zzm zzm) {
        zzlo zzlo = null;
        if (zzm.zzk() && GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12800000) == 0) {
            zzlo = zza.zzb(context, executor, zzm);
        }
        return zzlo == null ? new zzlk(context, executor, zzm) : zzlo;
    }

    private final zzlo zzb(Context context, Executor executor, zzm zzm) {
        try {
            IBinder zze = ((zzlp) getRemoteCreatorInstance(context)).zze(ObjectWrapper.wrap(context), ObjectWrapper.wrap(executor), zzm.zzav());
            if (zze == null) {
                return null;
            }
            IInterface queryLocalInterface = zze.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
            return queryLocalInterface instanceof zzlo ? (zzlo) queryLocalInterface : new zzlm(zze);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException | IllegalArgumentException | LinkageError unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
        return queryLocalInterface instanceof zzlp ? (zzlp) queryLocalInterface : new zzlp(iBinder);
    }
}
