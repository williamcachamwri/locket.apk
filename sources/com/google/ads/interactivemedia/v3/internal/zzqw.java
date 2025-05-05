package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzqw extends zzqr {
    final Object zza;
    int zzb;
    final /* synthetic */ zzrf zzc;

    zzqw(zzrf zzrf, int i) {
        this.zzc = zzrf;
        this.zza = zzrf.zza[i];
        this.zzb = i;
    }

    public final Object getKey() {
        return this.zza;
    }

    public final Object getValue() {
        zza();
        int i = this.zzb;
        if (i == -1) {
            return null;
        }
        return this.zzc.zzb[i];
    }

    public final Object setValue(Object obj) {
        zza();
        int i = this.zzb;
        if (i == -1) {
            this.zzc.zzg(this.zza, obj, false);
            return null;
        }
        Object obj2 = this.zzc.zzb[i];
        if (zzqe.zza(obj2, obj)) {
            return obj;
        }
        this.zzc.zzw(this.zzb, obj, false);
        return obj2;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        int i = this.zzb;
        if (i != -1) {
            zzrf zzrf = this.zzc;
            if (i <= zzrf.zzc && zzqe.zza(zzrf.zza[i], this.zza)) {
                return;
            }
        }
        zzrf zzrf2 = this.zzc;
        Object obj = this.zza;
        this.zzb = zzrf2.zzc(obj, zzrg.zzc(obj));
    }
}
