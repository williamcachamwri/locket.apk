package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsm extends zzacv implements zzaeg {
    private zzsm() {
        super(zzsn.zzb);
    }

    public final zzsm zza(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzsn) this.zza).zzf = i;
        return this;
    }

    public final zzsm zzb(zzsq zzsq) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzsn.zzg((zzsn) this.zza, zzsq);
        return this;
    }

    /* synthetic */ zzsm(zzsl zzsl) {
        super(zzsn.zzb);
    }
}
