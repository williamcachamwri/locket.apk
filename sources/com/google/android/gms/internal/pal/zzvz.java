package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvz extends zzacv implements zzaeg {
    private zzvz() {
        super(zzwa.zzb);
    }

    public final zzvz zza(zzvo zzvo) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzwa.zzf((zzwa) this.zza, zzvo);
        return this;
    }

    public final zzvz zzb(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwa) this.zza).zzg = i;
        return this;
    }

    public final zzvz zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwa) this.zza).zzh = zzwu.zza(i);
        return this;
    }

    public final zzvz zzd(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwa) this.zza).zzf = zzvq.zza(i);
        return this;
    }

    /* synthetic */ zzvz(zzvx zzvx) {
        super(zzwa.zzb);
    }
}
