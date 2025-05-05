package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzpj {
    final zzpm zza;
    final boolean zzb;

    private zzpj(zzpm zzpm) {
        this.zza = zzpm;
        this.zzb = zzpm != null;
    }

    public static zzpj zzb(Context context, String str, String str2) {
        zzpm zzpm;
        try {
            IBinder instantiate = DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite").instantiate("com.google.android.gms.gass.internal.clearcut.GassDynamiteClearcutLogger");
            IBinder iBinder = instantiate;
            if (instantiate == null) {
                zzpm = null;
            } else {
                IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.gass.internal.clearcut.IGassClearcut");
                zzpm = queryLocalInterface instanceof zzpm ? (zzpm) queryLocalInterface : new zzpk(instantiate);
            }
            try {
                zzpm zzpm2 = zzpm;
                zzpm.zze(ObjectWrapper.wrap(context), str, (String) null);
                Log.i("GASS", "GassClearcutLogger Initialized.");
                return new zzpj(zzpm);
            } catch (RemoteException | zzon | NullPointerException | SecurityException unused) {
                Log.d("GASS", "Cannot dynamite load clearcut");
                return new zzpj(new zzpn());
            }
        } catch (Exception e) {
            throw new zzon(e);
        } catch (Exception e2) {
            throw new zzon(e2);
        }
    }

    public static zzpj zzc() {
        zzpn zzpn = new zzpn();
        Log.d("GASS", "Clearcut logging disabled");
        return new zzpj(zzpn);
    }

    public final zzpi zza(byte[] bArr) {
        return new zzpi(this, bArr, (zzph) null);
    }
}
