package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwd extends zzacv implements zzaeg {
    private zzwd() {
        super(zzwg.zzb);
    }

    public final zzwd zza(zzwf zzwf) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzwg.zze((zzwg) this.zza, zzwf);
        return this;
    }

    public final zzwd zzb(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwg) this.zza).zze = i;
        return this;
    }

    /* synthetic */ zzwd(zzwc zzwc) {
        super(zzwg.zzb);
    }
}
