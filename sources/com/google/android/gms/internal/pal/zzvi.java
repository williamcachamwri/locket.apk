package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvi extends zzacv implements zzaeg {
    private zzvi() {
        super(zzvj.zzb);
    }

    public final zzvi zza(zzvd zzvd) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzvj.zzj((zzvj) this.zza, zzvd);
        return this;
    }

    public final zzvi zzb(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvj) this.zza).zzg = zzaby;
        return this;
    }

    public final zzvi zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvj) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzvi(zzvh zzvh) {
        super(zzvj.zzb);
    }
}
