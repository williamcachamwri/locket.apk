package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzur extends zzacv implements zzaeg {
    private zzur() {
        super(zzus.zzb);
    }

    public final zzur zza(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzus) this.zza).zzf = i;
        return this;
    }

    public final zzur zzb(zzuv zzuv) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzus.zzh((zzus) this.zza, zzuv);
        return this;
    }

    /* synthetic */ zzur(zzuq zzuq) {
        super(zzus.zzb);
    }
}
