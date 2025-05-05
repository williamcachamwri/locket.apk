package com.google.ads.interactivemedia.v3.internal;

import android.os.RemoteException;
import android.util.Log;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzpi {
    final /* synthetic */ zzpj zza;
    private final byte[] zzb;
    private int zzc;
    private int zzd;

    /* synthetic */ zzpi(zzpj zzpj, byte[] bArr, zzph zzph) {
        this.zza = zzpj;
        this.zzb = bArr;
    }

    public final zzpi zza(int i) {
        this.zzd = i;
        return this;
    }

    public final zzpi zzb(int i) {
        this.zzc = i;
        return this;
    }

    public final synchronized void zzc() {
        try {
            zzpj zzpj = this.zza;
            if (zzpj.zzb) {
                zzpj.zza.zzj(this.zzb);
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
