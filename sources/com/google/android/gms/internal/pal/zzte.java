package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzte extends zzacv implements zzaeg {
    private zzte() {
        super(zztf.zzb);
    }

    public final zzte zza(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zztf) this.zza).zzf = zzaby;
        return this;
    }

    public final zzte zzb(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zztf) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzte(zztd zztd) {
        super(zztf.zzb);
    }
}
