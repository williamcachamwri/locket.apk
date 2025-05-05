package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwi extends zzacv implements zzaeg {
    private zzwi() {
        super(zzwj.zzb);
    }

    public final zzwi zza(zzwm zzwm) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzwj.zzh((zzwj) this.zza, zzwm);
        return this;
    }

    public final zzwi zzb(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwj) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzwi(zzwh zzwh) {
        super(zzwj.zzb);
    }
}
