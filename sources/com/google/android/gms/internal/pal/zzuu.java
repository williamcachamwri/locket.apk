package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzuu extends zzacv implements zzaeg {
    private zzuu() {
        super(zzuv.zzb);
    }

    public final zzuu zza(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzuv) this.zza).zzf = i;
        return this;
    }

    public final zzuu zzb(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzuv) this.zza).zze = zzum.zza(i);
        return this;
    }

    /* synthetic */ zzuu(zzut zzut) {
        super(zzuv.zzb);
    }
}
