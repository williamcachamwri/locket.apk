package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Handler;
import com.google.ads.interactivemedia.omid.library.adsession.zze;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcz implements zzcs {
    private static zzcz zza;
    private float zzb = 0.0f;
    private zzcn zzc;
    private zzcr zzd;

    public zzcz(zzco zzco, zzcm zzcm) {
    }

    public static zzcz zzb() {
        if (zza == null) {
            zza = new zzcz(new zzco(), new zzcm());
        }
        return zza;
    }

    public final float zza() {
        return this.zzb;
    }

    public final void zzc(boolean z) {
        if (z) {
            zzdz.zzd().zzi();
        } else {
            zzdz.zzd().zzh();
        }
    }

    public final void zzd(Context context) {
        this.zzc = new zzcn(new Handler(), context, new zzcl(), this);
    }

    public final void zze(float f) {
        this.zzb = f;
        if (this.zzd == null) {
            this.zzd = zzcr.zza();
        }
        for (zze zzh : this.zzd.zzb()) {
            zzh.zzh().zzl(f);
        }
    }

    public final void zzf() {
        zzcq.zza().zze(this);
        zzcq.zza().zzf();
        zzdz.zzd().zzi();
        this.zzc.zza();
    }

    public final void zzg() {
        zzdz.zzd().zzj();
        zzcq.zza().zzg();
        this.zzc.zzb();
    }
}
