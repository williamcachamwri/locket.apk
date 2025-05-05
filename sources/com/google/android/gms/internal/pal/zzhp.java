package com.google.android.gms.internal.pal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzhp {
    final zzhs zza;
    final boolean zzb;

    private zzhp(zzhs zzhs) {
        this.zza = zzhs;
        this.zzb = zzhs != null;
    }

    public static zzhp zzb(Context context, String str, String str2) {
        zzhs zzhs;
        try {
            IBinder instantiate = DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.ads.dynamite").instantiate("com.google.android.gms.gass.internal.clearcut.GassDynamiteClearcutLogger");
            if (instantiate == null) {
                zzhs = null;
            } else {
                IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.gass.internal.clearcut.IGassClearcut");
                zzhs = queryLocalInterface instanceof zzhs ? (zzhs) queryLocalInterface : new zzhq(instantiate);
            }
            try {
                zzhs.zze(ObjectWrapper.wrap(context), "ADSHIELD", (String) null);
                Log.i("GASS", "GassClearcutLogger Initialized.");
                return new zzhp(zzhs);
            } catch (RemoteException | zzhg | NullPointerException | SecurityException unused) {
                Log.d("GASS", "Cannot dynamite load clearcut");
                return new zzhp(new zzht());
            }
        } catch (Exception e) {
            throw new zzhg(e);
        } catch (Exception e2) {
            throw new zzhg(e2);
        }
    }

    public final zzho zza(byte[] bArr) {
        return new zzho(this, bArr, (zzhn) null);
    }
}
