package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzkc implements Callable {
    private final zzjj zza;
    private final zzan zzb;

    public zzkc(zzjj zzjj, zzan zzan) {
        this.zza = zzjj;
        this.zzb = zzan;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        if (this.zza.zzl() != null) {
            this.zza.zzl().get();
        }
        zzbp zzc = this.zza.zzc();
        if (zzc == null) {
            return null;
        }
        try {
            synchronized (this.zzb) {
                zzan zzan = this.zzb;
                byte[] zzav = zzc.zzav();
                zzan.zzak(zzav, 0, zzav.length, zzadk.zza());
            }
            return null;
        } catch (zzaeg | NullPointerException unused) {
            return null;
        }
    }
}
