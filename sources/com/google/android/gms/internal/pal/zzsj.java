package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsj extends zzacv implements zzaeg {
    private zzsj() {
        super(zzsk.zzb);
    }

    public final zzsj zza(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzsk) this.zza).zzg = zzaby;
        return this;
    }

    public final zzsj zzb(zzsq zzsq) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzsk.zzi((zzsk) this.zza, zzsq);
        return this;
    }

    public final zzsj zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzsk) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzsj(zzsi zzsi) {
        super(zzsk.zzb);
    }
}
