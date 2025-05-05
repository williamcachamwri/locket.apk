package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzvs extends zzacv implements zzaeg {
    private zzvs() {
        super(zzvt.zzb);
    }

    public final zzvs zza(String str) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzvt.zzg((zzvt) this.zza, str);
        return this;
    }

    public final zzvs zzb(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvt) this.zza).zzf = zzaby;
        return this;
    }

    public final zzvs zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzvt) this.zza).zzg = zzwu.zza(i);
        return this;
    }

    /* synthetic */ zzvs(zzvr zzvr) {
        super(zzvt.zzb);
    }
}
