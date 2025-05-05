package com.google.android.gms.internal.pal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzfo extends RemoteCreator {
    private static final zzfo zza = new zzfo();

    private zzfo() {
        super("com.google.android.gms.ads.adshield.AdShieldCreatorImpl");
    }

    @Deprecated
    public static zzfr zza(String str, Context context, boolean z, boolean z2) {
        zzfr zzb = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12800000) == 0 ? zza.zzb("h.3.2.2/n.android.3.2.2", context, false) : null;
        return zzb == null ? new zzfn("h.3.2.2/n.android.3.2.2", context, false) : zzb;
    }

    private final zzfr zzb(String str, Context context, boolean z) {
        try {
            IBinder zze = ((zzfs) getRemoteCreatorInstance(context)).zze("h.3.2.2/n.android.3.2.2", ObjectWrapper.wrap(context));
            if (zze == null) {
                return null;
            }
            IInterface queryLocalInterface = zze.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldClient");
            return queryLocalInterface instanceof zzfr ? (zzfr) queryLocalInterface : new zzfp(zze);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException | LinkageError unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.adshield.internal.IAdShieldCreator");
        return queryLocalInterface instanceof zzfs ? (zzfs) queryLocalInterface : new zzfs(iBinder);
    }
}
