package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzzv<T> extends zzzr<T> {
    final zzvr zza;
    private final zzwd zzb;
    private final zzvv zzc;
    private final zzaca zzd;
    private final zzwk zze;
    private final zzzt zzf = new zzzt(this, (zzzs) null);
    private final boolean zzg;
    private volatile zzwj zzh;

    public zzzv(zzwd zzwd, zzvv zzvv, zzvr zzvr, zzaca zzaca, zzwk zzwk, boolean z) {
        this.zzb = zzwd;
        this.zzc = zzvv;
        this.zza = zzvr;
        this.zzd = zzaca;
        this.zze = zzwk;
        this.zzg = z;
    }

    public static zzwk zzb(zzaca zzaca, Object obj) {
        return new zzzu(obj, zzaca, zzaca.zzd() == zzaca.zzc(), (Class) null);
    }

    private final zzwj zzc() {
        zzwj zzwj = this.zzh;
        if (zzwj != null) {
            return zzwj;
        }
        zzwj zzb2 = this.zza.zzb(this.zze, this.zzd);
        this.zzh = zzb2;
        return zzb2;
    }

    public final T read(zzacc zzacc) throws IOException {
        if (this.zzc == null) {
            return zzc().read(zzacc);
        }
        zzvw zza2 = zzyg.zza(zzacc);
        if (this.zzg && (zza2 instanceof zzvy)) {
            return null;
        }
        zzvv zzvv = this.zzc;
        this.zzd.zzd();
        return zzvv.zza();
    }

    public final void write(zzace zzace, T t) throws IOException {
        zzwd zzwd = this.zzb;
        if (zzwd == null) {
            zzc().write(zzace, t);
        } else if (!this.zzg || t != null) {
            zzaca zzaca = this.zzd;
            zzvw zza2 = zzwd.zza(t, zzaca.zzd(), this.zzf);
            zzvw zzvw = zza2;
            ((zzaaq) zzabh.zzV).write(zzace, zza2);
        } else {
            zzace.zzg();
        }
    }

    public final zzwj zza() {
        return this.zzb != null ? this : zzc();
    }
}
