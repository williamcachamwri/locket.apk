package com.google.android.gms.internal.atv_ads_framework;

import java.io.IOException;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzes implements zzey {
    private final zzeo zza;
    private final zzfp zzb;
    private final boolean zzc;
    private final zzcy zzd;

    private zzes(zzfp zzfp, zzcy zzcy, zzeo zzeo) {
        this.zzb = zzfp;
        this.zzc = zzcy.zzc(zzeo);
        this.zzd = zzcy;
        this.zza = zzeo;
    }

    static zzes zzi(zzfp zzfp, zzcy zzcy, zzeo zzeo) {
        return new zzes(zzfp, zzcy, zzeo);
    }

    public final int zza(Object obj) {
        zzfp zzfp = this.zzb;
        int zzb2 = zzfp.zzb(zzfp.zzc(obj));
        if (!this.zzc) {
            return zzb2;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzc(obj).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public final Object zzc() {
        zzeo zzeo = this.zza;
        if (zzeo instanceof zzdh) {
            return ((zzdh) zzeo).zzq();
        }
        return zzeo.zzs().zzk();
    }

    public final void zzd(Object obj) {
        this.zzb.zze(obj);
        this.zzd.zzb(obj);
    }

    public final void zze(Object obj, Object obj2) {
        zzfa.zzA(this.zzb, obj, obj2);
        if (this.zzc) {
            this.zzd.zza(obj2);
            throw null;
        }
    }

    public final void zzf(Object obj, zzgg zzgg) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }

    public final boolean zzg(Object obj, Object obj2) {
        if (!this.zzb.zzc(obj).equals(this.zzb.zzc(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        this.zzd.zza(obj2);
        throw null;
    }

    public final boolean zzh(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }
}
