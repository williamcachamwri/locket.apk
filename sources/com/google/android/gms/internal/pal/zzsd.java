package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzsd extends zzacv implements zzaeg {
    private zzsd() {
        super(zzse.zzb);
    }

    public final zzsd zza(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzse) this.zza).zzf = i;
        return this;
    }

    public final zzsd zzb(zzsh zzsh) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzse.zzh((zzse) this.zza, zzsh);
        return this;
    }

    /* synthetic */ zzsd(zzsc zzsc) {
        super(zzse.zzb);
    }
}
