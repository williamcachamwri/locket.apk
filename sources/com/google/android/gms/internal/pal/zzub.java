package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzub extends zzacv implements zzaeg {
    private zzub() {
        super(zzuc.zzb);
    }

    public final zzub zza(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzuc) this.zza).zzg = zzaby;
        return this;
    }

    public final zzub zzb(zzuf zzuf) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzuc.zzi((zzuc) this.zza, zzuf);
        return this;
    }

    public final zzub zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzuc) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzub(zzua zzua) {
        super(zzuc.zzb);
    }
}
