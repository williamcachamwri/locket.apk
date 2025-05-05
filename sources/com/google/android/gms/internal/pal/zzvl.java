package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvl extends zzacv implements zzaeg {
    private zzvl() {
        super(zzvo.zzb);
    }

    public final zzvl zza(zzvn zzvn) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvo) this.zza).zzg = zzvn.zza();
        return this;
    }

    public final zzvl zzb(String str) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzvo.zzh((zzvo) this.zza, str);
        return this;
    }

    public final zzvl zzc(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvo) this.zza).zzf = zzaby;
        return this;
    }

    /* synthetic */ zzvl(zzvk zzvk) {
        super(zzvo.zzb);
    }
}
