package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvy extends zzacv implements zzaeg {
    private zzvy() {
        super(zzwb.zzb);
    }

    public final zzvy zza(zzwa zzwa) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzwb.zzi((zzwb) this.zza, zzwa);
        return this;
    }

    public final zzvy zzb(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwb) this.zza).zze = i;
        return this;
    }

    /* synthetic */ zzvy(zzvx zzvx) {
        super(zzwb.zzb);
    }
}
