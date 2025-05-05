package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvf extends zzacv implements zzaeg {
    private zzvf() {
        super(zzvg.zzb);
    }

    public final zzvf zza(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvg) this.zza).zzg = zzaby;
        return this;
    }

    public final zzvf zzb(zzvj zzvj) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzvg.zzi((zzvg) this.zza, zzvj);
        return this;
    }

    public final zzvf zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvg) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzvf(zzve zzve) {
        super(zzvg.zzb);
    }
}
