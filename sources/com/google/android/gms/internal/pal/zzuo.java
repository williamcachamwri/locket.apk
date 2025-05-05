package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzuo extends zzacv implements zzaeg {
    private zzuo() {
        super(zzup.zzb);
    }

    public final zzuo zza(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzup) this.zza).zzg = zzaby;
        return this;
    }

    public final zzuo zzb(zzuv zzuv) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzup.zzj((zzup) this.zza, zzuv);
        return this;
    }

    public final zzuo zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzup) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzuo(zzun zzun) {
        super(zzup.zzb);
    }
}
