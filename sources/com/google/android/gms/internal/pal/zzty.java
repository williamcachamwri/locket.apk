package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzty extends zzacv implements zzaeg {
    private zzty() {
        super(zztz.zzb);
    }

    public final zzty zza(zztt zztt) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zztz.zzh((zztz) this.zza, zztt);
        return this;
    }

    public final zzty zzb(zzui zzui) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zztz.zzg((zztz) this.zza, zzui);
        return this;
    }

    public final zzty zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zztz) this.zza).zzg = zztq.zza(i);
        return this;
    }

    /* synthetic */ zzty(zztx zztx) {
        super(zztz.zzb);
    }
}
