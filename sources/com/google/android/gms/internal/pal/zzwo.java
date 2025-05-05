package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwo extends zzacv implements zzaeg {
    private zzwo() {
        super(zzwp.zzb);
    }

    public final zzwo zza(zzws zzws) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzwp.zzh((zzwp) this.zza, zzws);
        return this;
    }

    public final zzwo zzb(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwp) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzwo(zzwn zzwn) {
        super(zzwp.zzb);
    }
}
