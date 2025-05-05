package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzzu implements zzwk {
    private final zzaca zza;
    private final boolean zzb;
    private final zzwd zzc;
    private final zzvv zzd;

    zzzu(Object obj, zzaca zzaca, boolean z, Class cls) {
        zzvv zzvv = null;
        zzwd zzwd = obj instanceof zzwd ? (zzwd) obj : null;
        this.zzc = zzwd;
        zzvv = obj instanceof zzvv ? (zzvv) obj : zzvv;
        this.zzd = zzvv;
        boolean z2 = true;
        if (zzwd == null && zzvv == null) {
            z2 = false;
        }
        zzwn.zza(z2);
        this.zza = zzaca;
        this.zzb = z;
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        if (this.zza.equals(zzaca) || (this.zzb && this.zza.zzd() == zzaca.zzc())) {
            return new zzzv(this.zzc, this.zzd, zzvr, zzaca, this, true);
        }
        return null;
    }
}
