package com.google.android.gms.internal.pal;

import android.os.RemoteException;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzho {
    final /* synthetic */ zzhp zza;
    private final byte[] zzb;
    private int zzc;
    private int zzd;

    /* synthetic */ zzho(zzhp zzhp, byte[] bArr, zzhn zzhn) {
        this.zza = zzhp;
        this.zzb = bArr;
    }

    public final zzho zza(int i) {
        this.zzd = i;
        return this;
    }

    public final zzho zzb(int i) {
        this.zzc = i;
        return this;
    }

    public final synchronized void zzc() {
        try {
            zzhp zzhp = this.zza;
            if (zzhp.zzb) {
                zzhp.zza.zzj(this.zzb);
                this.zza.zza.zzi(this.zzc);
                this.zza.zza.zzg(this.zzd);
                this.zza.zza.zzh((int[]) null);
                this.zza.zza.zzf();
            }
        } catch (RemoteException e) {
            Log.d("GASS", "Clearcut log failed", e);
        }
    }
}
