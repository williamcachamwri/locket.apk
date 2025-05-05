package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsa extends zzacv implements zzaeg {
    private zzsa() {
        super(zzsb.zzb);
    }

    public final zzsa zza(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzsb) this.zza).zzg = zzaby;
        return this;
    }

    public final zzsa zzb(zzsh zzsh) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzsb.zzj((zzsb) this.zza, zzsh);
        return this;
    }

    public final zzsa zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzsb) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzsa(zzrz zzrz) {
        super(zzsb.zzb);
    }
}
