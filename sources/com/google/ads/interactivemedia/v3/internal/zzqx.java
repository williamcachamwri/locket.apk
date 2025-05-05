package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzqx extends zzqr {
    final zzrf zza;
    final Object zzb;
    int zzc;

    zzqx(zzrf zzrf, int i) {
        this.zza = zzrf;
        this.zzb = zzrf.zzb[i];
        this.zzc = i;
    }

    private final void zza() {
        int i = this.zzc;
        if (i != -1) {
            zzrf zzrf = this.zza;
            if (i <= zzrf.zzc && zzqe.zza(this.zzb, zzrf.zzb[i])) {
                return;
            }
        }
        zzrf zzrf2 = this.zza;
        Object obj = this.zzb;
        this.zzc = zzrf2.zzd(obj, zzrg.zzc(obj));
    }

    public final Object getKey() {
        return this.zzb;
    }

    public final Object getValue() {
        zza();
        int i = this.zzc;
        if (i == -1) {
            return null;
        }
        return this.zza.zza[i];
    }

    public final Object setValue(Object obj) {
        zza();
        int i = this.zzc;
        if (i == -1) {
            this.zza.zzh(this.zzb, obj, false);
            return null;
        }
        Object obj2 = this.zza.zza[i];
        if (zzqe.zza(obj2, obj)) {
            return obj;
        }
        this.zza.zzv(this.zzc, obj, false);
        return obj2;
    }
}
