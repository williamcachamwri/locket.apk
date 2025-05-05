package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzxm extends zzwj {
    final /* synthetic */ boolean zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzvr zzc;
    final /* synthetic */ zzaca zzd;
    final /* synthetic */ zzxn zze;
    private volatile zzwj zzf;

    zzxm(zzxn zzxn, boolean z, boolean z2, zzvr zzvr, zzaca zzaca) {
        this.zza = z;
        this.zzb = z2;
        this.zzc = zzvr;
        this.zzd = zzaca;
        this.zze = zzxn;
    }

    private final zzwj zza() {
        zzwj zzwj = this.zzf;
        if (zzwj != null) {
            return zzwj;
        }
        zzwj zzb2 = this.zzc.zzb(this.zze, this.zzd);
        this.zzf = zzb2;
        return zzb2;
    }

    public final Object read(zzacc zzacc) throws IOException {
        if (!this.zza) {
            return zza().read(zzacc);
        }
        zzacc.zzo();
        return null;
    }

    public final void write(zzace zzace, Object obj) throws IOException {
        if (this.zzb) {
            zzace.zzg();
        } else {
            zza().write(zzace, obj);
        }
    }
}
